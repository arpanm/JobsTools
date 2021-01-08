package com.quikr.jobs.tools;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.quikr.jobs.tools");

        noClasses()
            .that()
                .resideInAnyPackage("com.quikr.jobs.tools.service..")
            .or()
                .resideInAnyPackage("com.quikr.jobs.tools.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.quikr.jobs.tools.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
