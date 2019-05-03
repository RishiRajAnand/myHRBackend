/*
* created by Raushan on 15-02-2018
* */
package com.erx.microservice.patientmanagement.service.exportcampcsvfile;


import com.erx.microservice.patientmanagement.domain.AddressInfo;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.PatientCampRegistration;
import com.erx.microservice.patientmanagement.domain.PatientUhIdentifier;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.PackageCatalogueRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


@Service
public class ExportCampCSVFileServiceImpl implements ExportCampCSVFileService {
    private final static Logger log = LoggerFactory.getLogger(ExportCampCSVFileService.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PackageCatalogueRepository packageCatalogueRepository;
    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public ExportCampCSVFileServiceResponse execute(ExportCampCSVFileServiceRequest request) throws ServiceException {
        log.debug(" Enters ExportCSVFileServiceImpl ----> execute ");
        ExportCampCSVFileServiceResponse response = new ExportCampCSVFileServiceResponse();
        Long clinicId = null;
        OutputStream output = null;
        InputStream fileInputStream = null;
        String fileCopy = null;
        try {
            if (request.getClinicId() != null) {
                File file = createFile(request.getClinicId());
                fileCopy = file.getAbsolutePath();
                if (file != null) // if the file is present
                {
                    log.info("downloading file.. " + file.getName());
                    String fileName = System.currentTimeMillis() + ".csv";
                    fileInputStream = new FileInputStream(file);
                    output = request.getHttpServletResponse().getOutputStream();
                    request.getHttpServletResponse().reset();
                    request.getHttpServletResponse().setContentType("text/CSV");
                    request.getHttpServletResponse().setContentLength((int) (file.length()));
                    request.getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    IOUtils.copyLarge(fileInputStream, output);
                    output.flush();
                    log.info("downloading file completed.. " + file.getName());
                }
            }
            response = new ExportCampCSVFileServiceResponse();
            response.SUCCESSFUL = true;
            response.setMessage("Export Done successfully");
        } catch (Exception e) {
            //Create response
            response = new ExportCampCSVFileServiceResponse();
            log.error("ExportCSVFileServiceImpl ----> execute " + e.getMessage());
            response.setMessage("Error on Exporting Data .");
            response.SUCCESSFUL = false;
        } finally {
            try {
                //finally closing output and fileInputStream and deletingFile
                output.close();
                fileInputStream.close();
                File absolutePath = new File(fileCopy);
                if (absolutePath.exists()) {
                    if (absolutePath.delete())
                        log.info(absolutePath.getName() + " is deleted!!");
                    else
                        log.info(absolutePath.getName() + " not deleted");
                }

            } catch (IOException e) {
                request.getHttpServletResponse().setHeader("message", "could not download the CAMP File");
            }
        }
        return response;
    }


    @Override
    public File createFile(Long clinicId) throws ServiceException {
        log.debug(" Enters ExportCSVFileServiceImpl ----> createFile ");
        List<Object[]> listOfObjects = null;
        PatientCampRegistration patientCampRegistration = null;
        AddressInfo addressInfo = null;
        Patient patient = null;
        PatientUhIdentifier patientUhIdentifier = null;
        String cvsSplitBy = ",";
        FileWriter fileWriter = null;
        String lineSeparator = "\n";
        File file = null;
        try {
            Path currentRelativePath = Paths.get("");
            String currentdirectory = currentRelativePath.toAbsolutePath().toString();
            file = new File(currentdirectory + "/" + "downloadcsv" + "/" + "export_Patient.csv");
            file.getParentFile().mkdirs();
            file.createNewFile();
            fileWriter = new FileWriter(file);
            //Write the CSV file header
            String FILE_HEADER = "MRN,Patient Name *,Mobile Number*,Email,Date of Birth*(ex : 01-Jan-2000),Gender (Male/Female/Undefined)*,Universal Identification Type,Universal Identification Number,Language (en/hi/gu/kn)*,External ID,Address,City,State,Postal Code,Country,Package Name,Camp Date";
            fileWriter.append(FILE_HEADER.toString());
            //Add a new line separator after the header
            fileWriter.append(lineSeparator);
            listOfObjects = patientRepository.getALLPatientDetailByClinic(clinicId);
            for (Object[] object : listOfObjects) {
                patient = (Patient) object[0];
                addressInfo = (AddressInfo) object[1];
                patientUhIdentifier = (PatientUhIdentifier) object[2];
                patientCampRegistration = (PatientCampRegistration) object[3];
                if (patient != null) {
                    if (patient.getPatientIdExternal() != null) {
                        fileWriter.append(patient.getPatientIdExternal());
                    } else {
                        fileWriter.append("null");
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patient.getPatientName() != null) {
                        fileWriter.append(patient.getPatientName());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patient.getMobileNumber() != null) {
                        fileWriter.append(patient.getMobileNumber());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patient.getEmail() != null) {
                        fileWriter.append(patient.getEmail());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patient.getDateOfBirth() != null) {
                        String dateOfBirth = formatDate(patient.getDateOfBirth()).replaceAll(" ", "-");
                        fileWriter.append(dateOfBirth);
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patient.getGender() != null) {
                        fileWriter.append(patient.getGender());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patientUhIdentifier != null && patientUhIdentifier.getUhIdentifier() != null) {
                        fileWriter.append(patientUhIdentifier.getUhIdentifier().getName());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patientUhIdentifier != null && patientUhIdentifier.getIdentifier_number() != null) {
                        fileWriter.append(patientUhIdentifier.getIdentifier_number());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patient.getLanguage() != null) {
                        fileWriter.append(patient.getLanguage());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patient.getPatientIdExternal() != null) {
                        fileWriter.append(patient.getPatientIdExternal());
                    }
                    fileWriter.append(cvsSplitBy);
                    if (addressInfo != null) {
                        if (addressInfo.getContactAddress() != null) {
                            fileWriter.append(addressInfo.getContactAddress());
                        }
                        fileWriter.append(cvsSplitBy);
                        if (addressInfo.getCity() != null) {
                            fileWriter.append(addressInfo.getCity().getName());
                        }
                        fileWriter.append(cvsSplitBy);
                        if (addressInfo.getState() != null) {
                            fileWriter.append(addressInfo.getState().getName());
                        }
                        fileWriter.append(cvsSplitBy);
                        if (addressInfo.getPincode() != null) {
                            fileWriter.append(addressInfo.getPincode().getPin());
                        }
                        fileWriter.append(cvsSplitBy);
                        if (addressInfo.getCountry() != null) {
                            fileWriter.append(addressInfo.getCountry().getName());

                        }
                        fileWriter.append(cvsSplitBy);

                    }
                    if (patientCampRegistration != null && patientCampRegistration.getPackageCatalogueId() != null) {
                        Long packageCatalogueId = patientCampRegistration.getPackageCatalogueId();
                        if (packageCatalogueId != null) {
                            String packageName = getPackageNameById(packageCatalogueId);
                            fileWriter.append(packageName);
                        }
                    }
                    fileWriter.append(cvsSplitBy);
                    if (patientCampRegistration != null) {
                        String campDate = formatDate(patientCampRegistration.getCreatedOn().toLocalDate()).replaceAll(" ", "-");
                        fileWriter.append(campDate);
                    }
                    fileWriter.append(lineSeparator);
                }
            }
            log.info("CSV file was created successfully !!!");
        } catch (Exception e) {
            log.error("Error in CsvFileWriter !!!" + e);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                log.error("Error while flushing/closing fileWriter !!!" + e);
            }
        }
        return file;
    }

    public String formatDate(LocalDate createdOn) {
        String formatDate;
        DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = parser.parse(String.valueOf(createdOn));
        } catch (ParseException e) {
        }
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        formatDate = formatter.format(date);
        //remove hyphen from formatted date
        return formatDate.replace('-', ' ');
    }

    private String getPackageNameById(Long packageCatalogueId) throws Exception {
        String packageName = null;
        JSONObject packageCatalogueJSONObject = new JSONObject();
        //retrieve the package by name
        try {
            JSONObject jsonObject = serviceGateway.getPackageCatalogueById(packageCatalogueId);
            if (jsonObject != null) {
                packageCatalogueJSONObject = new JSONObject(jsonObject.getString("packageCatalogueDTO"));
                packageName = packageCatalogueJSONObject.getString("packageName");
            }
        } catch (Exception e) {
            log.debug("ExportCampCSVFileServiceImpl ==> getPackageNameById ==> Package not found : " + e.getMessage());
        }
        return packageName;
    }
}
