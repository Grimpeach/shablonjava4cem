package org.example.practice15.service;


import org.example.practice15.model.Manufacture;
import org.example.practice15.model.Phone;
import org.example.practice15.repository.ManufactureRepository;
import org.example.practice15.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@ManagedResource(objectName = "org.example.practice15.service:name=DataExportService")
public class DataExportService {

    private static final Logger logger = LoggerFactory.getLogger(DataExportService.class);

    private final PhoneRepository phoneRepository;
    private final ManufactureRepository manufactureRepository;

    @Value(value = "${data.export.directory}")
    private String exportDirectoryPath;

    public DataExportService(PhoneRepository phoneRepository, ManufactureRepository manufactureRepository) {
        this.phoneRepository = phoneRepository;
        this.manufactureRepository = manufactureRepository;
    }

    @PostConstruct
    public void init() {
        // Создаем директорию, если она не существует
        File exportDirectory = new File(exportDirectoryPath);
        if (!exportDirectory.exists()) {
            exportDirectory.mkdirs();
        }
    }

    @Scheduled(fixedRate = 1800000)  // 30 минут = 1800000 миллисекунд
    public void exportData() {
        logger.info("Starting data export...");
        clearExportDirectory();
        exportPhones();
        exportManufactures();
        logger.info("Data export completed.");
    }

    @ManagedOperation(description = "Trigger data export")
    public void triggerDataExport() {
        exportData();
    }

    private void clearExportDirectory() {
        logger.info("Clearing export directory: {}", exportDirectoryPath);
        File directory = new File(exportDirectoryPath);
        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (!file.isDirectory()) {
                    boolean deleted = file.delete();
                    if (deleted) {
                        logger.info("Deleted file: {}", file.getName());
                    } else {
                        logger.warn("Failed to delete file: {}", file.getName());
                    }
                }
            }
        }
    }

    private void exportPhones() {
        List<Phone> phones = phoneRepository.findAll();
        File file = new File(exportDirectoryPath, "phones.txt");

        try (FileWriter writer = new FileWriter(file)) {
            for (Phone phone : phones) {
                writer.write(phone.toString() + System.lineSeparator());
            }
            logger.info("Exported {} phones to {}", phones.size(), file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Error exporting phones", e);
        }
    }

    private void exportManufactures() {
        List<Manufacture> manufactures = manufactureRepository.findAll();
        File file = new File(exportDirectoryPath, "manufactures.txt");

        try (FileWriter writer = new FileWriter(file)) {
            for (Manufacture manufacture : manufactures) {
                writer.write(manufacture.toString() + System.lineSeparator());
            }
            logger.info("Exported {} manufactures to {}", manufactures.size(), file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Error exporting manufactures", e);
        }
    }

    @ManagedAttribute
    public String getExportDirectoryPath() {
        return exportDirectoryPath;
    }

    @ManagedAttribute
    public void setExportDirectoryPath(String exportDirectoryPath) {
        this.exportDirectoryPath = exportDirectoryPath;
    }
}
