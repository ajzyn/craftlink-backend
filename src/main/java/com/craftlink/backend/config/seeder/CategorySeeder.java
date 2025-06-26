package com.craftlink.backend.config.seeder;

import com.craftlink.backend.category.entities.CategoryEntity;
import com.craftlink.backend.category.entities.CategoryImageEntity;
import com.craftlink.backend.category.repositories.CategoryRepository;
import com.craftlink.backend.service.entities.ServiceEntity;
import com.craftlink.backend.service.repositories.ServiceRepository;
import com.craftlink.backend.shared.enums.EntityStatus;
import com.craftlink.backend.shared.enums.ImageStatus;
import com.craftlink.backend.shared.utils.SlugUtils;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Order(1)
@ConditionalOnProperty(
    value = "app.database.seed",
    havingValue = "true",
    matchIfMissing = false
)
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            log.info("starting category seeding...");
            seedCategories();
            log.info("category seeding completed successfully!");
        } else {
            log.info("categories already exist, skipping seeding");
        }
    }

    private void seedCategories() {
        var categoriesData = getCategoriesData();

        for (CategoryData categoryData : categoriesData) {
            CategoryEntity category = createCategory(categoryData);
            CategoryEntity savedCategory = categoryRepository.save(category);

            createServicesForCategory(savedCategory, categoryData.services());

            log.info("created category: {} with {} services",
                categoryData.name(), categoryData.services().size());
        }
    }

    private CategoryEntity createCategory(CategoryData data) {
        var image = CategoryImageEntity.builder()
            .imageKey(data.imageKey())
            .originalFileName(data.imageKey())
            .imageStatus(ImageStatus.COMPLETED)
            .contentType("image/jpeg")
            .fileSize(110L * 1024)
            .userId("system")
            .uploadCompletedAt(Instant.now())
            .build();

        CategoryEntity category = new CategoryEntity();
        category.setName(data.name());
        category.setSlug(data.slug());
        category.setDescription(data.description());
        category.setIconName(data.iconName());
        category.setImage(image);

        return category;
    }

    private void createServicesForCategory(CategoryEntity category, List<ServiceData> servicesData) {
        for (ServiceData serviceData : servicesData) {
            ServiceEntity service = new ServiceEntity();
            service.setName(serviceData.name);
            service.setSlug(SlugUtils.generateSlug(serviceData.name));
            service.setCategory(category);
            service.setDescription(serviceData.description);
            service.setActive(EntityStatus.ACTIVE);

            serviceRepository.save(service);
        }
    }

    private List<CategoryData> getCategoriesData() {
        return List.of(
            new CategoryData(
                "Prace budowlane",
                "prace-budowlane",
                "Kompleksowe usługi budowlane - od fundamentów po wykończenie. Profesjonalni wykonawcy z wieloletnim doświadczeniem.",
                "hammer",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Budowa domów jednorodzinnych",
                        "Budowa domów jednorodzinnych – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów."),
                    new ServiceData("Rozbiórki i wyburzenia",
                        "Rozbiórki i wyburzenia – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów."),
                    new ServiceData("Budowa garaży",
                        "Budowa garaży – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów."),
                    new ServiceData("Fundamenty i izolacje",
                        "Fundamenty i izolacje – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów."),
                    new ServiceData("Murowanie ścian",
                        "Murowanie ścian – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów."),
                    new ServiceData("Budowa tarasów i werand",
                        "Budowa tarasów i werand – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów."),
                    new ServiceData("Remonty generalne",
                        "Remonty generalne – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów."),
                    new ServiceData("Nadbudowy i przebudowy",
                        "Nadbudowy i przebudowy – Usługa związana z pracami budowlanymi wykonywana przez doświadczonych specjalistów.")
                )
            ),
            new CategoryData(
                "Elektryka",
                "elektryka",
                "Profesjonalne usługi elektryczne - instalacje, naprawy, modernizacje. Bezpieczeństwo i jakość gwarantowana.",
                "zap",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Instalacje elektryczne",
                        "Instalacje elektryczne – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa."),
                    new ServiceData("Wymiana skrzynek bezpiecznikowych",
                        "Wymiana skrzynek bezpiecznikowych – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa."),
                    new ServiceData("Montaż oświetlenia",
                        "Montaż oświetlenia – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa."),
                    new ServiceData("Instalacja gniazdek i wyłączników",
                        "Instalacja gniazdek i wyłączników – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa."),
                    new ServiceData("Pomiary elektryczne",
                        "Pomiary elektryczne – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa."),
                    new ServiceData("Naprawa sprzętu elektronicznego",
                        "Naprawa sprzętu elektronicznego – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa."),
                    new ServiceData("Instalacje alarmowe",
                        "Instalacje alarmowe – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa."),
                    new ServiceData("Domofony i videodomofony",
                        "Domofony i videodomofony – Profesjonalna usługa elektryczna wykonywana zgodnie z normami bezpieczeństwa.")
                )
            ),
            new CategoryData(
                "Hydraulika",
                "hydraulika",
                "Kompleksowe usługi hydrauliczne - od drobnych napraw po kompletne instalacje. Szybko, solidnie, profesjonalnie.",
                "droplet",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Naprawa kranów i baterii",
                        "Naprawa kranów i baterii – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych."),
                    new ServiceData("Udrażnianie rur",
                        "Udrażnianie rur – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych."),
                    new ServiceData("Instalacje wodno-kanalizacyjne",
                        "Instalacje wodno-kanalizacyjne – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych."),
                    new ServiceData("Montaż bojlerów i podgrzewaczy",
                        "Montaż bojlerów i podgrzewaczy – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych."),
                    new ServiceData("Wymiana sedesów i umywalek",
                        "Wymiana sedesów i umywalek – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych."),
                    new ServiceData("Instalacje centralnego ogrzewania",
                        "Instalacje centralnego ogrzewania – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych."),
                    new ServiceData("Naprawa przecieków",
                        "Naprawa przecieków – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych."),
                    new ServiceData("Montaż systemów filtracji wody",
                        "Montaż systemów filtracji wody – Solidna usługa hydrauliczna zapewniająca sprawność instalacji wodnych.")
                )
            ),
            new CategoryData(
                "Wykończenia wnętrz",
                "wykończenia-wnetrz",
                "Profesjonalne wykończenia wnętrz - malowanie, gładzie, tapetowanie, układanie płytek. Perfekcyjne detale.",
                "paintbrush",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Malowanie ścian i sufitów",
                        "Malowanie ścian i sufitów – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz."),
                    new ServiceData("Układanie płytek ceramicznych",
                        "Układanie płytek ceramicznych – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz."),
                    new ServiceData("Gładzie i szpachlowanie",
                        "Gładzie i szpachlowanie – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz."),
                    new ServiceData("Tapetowanie",
                        "Tapetowanie – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz."),
                    new ServiceData("Montaż paneli podłogowych",
                        "Montaż paneli podłogowych – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz."),
                    new ServiceData("Układanie parkietu",
                        "Układanie parkietu – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz."),
                    new ServiceData("Montaż sufitów podwieszanych",
                        "Montaż sufitów podwieszanych – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz."),
                    new ServiceData("Szpachlowanie i lakierowanie",
                        "Szpachlowanie i lakierowanie – Usługa wykończeniowa poprawiająca estetykę i funkcjonalność wnętrz.")
                )
            ),
            new CategoryData(
                "Ogrodnictwo i zieleń",
                "ogrodnictwo",
                "Kompleksowa pielęgnacja ogrodów - projektowanie, sadzenie, pielęgnacja. Twój ogród marzeń na wyciągnięcie ręki.",
                "leaf",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Koszenie trawy",
                        "Koszenie trawy – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu."),
                    new ServiceData("Projektowanie ogrodów",
                        "Projektowanie ogrodów – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu."),
                    new ServiceData("Sadzenie drzew i krzewów",
                        "Sadzenie drzew i krzewów – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu."),
                    new ServiceData("Pielęgnacja trawników",
                        "Pielęgnacja trawników – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu."),
                    new ServiceData("Przycinanie żywopłotów",
                        "Przycinanie żywopłotów – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu."),
                    new ServiceData("Systemy nawadniania",
                        "Systemy nawadniania – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu."),
                    new ServiceData("Ogrody zimowe",
                        "Ogrody zimowe – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu."),
                    new ServiceData("Usuwanie drzew",
                        "Usuwanie drzew – Usługa ogrodnicza dbająca o wygląd i zdrowie roślin w Twoim otoczeniu.")
                )
            ),
            new CategoryData(
                "Stolarstwo i meble",
                "stolarstwo",
                "Precyzyjne usługi stolarskie - meble na wymiar, zabudowy, naprawy. Jakość drewna spotyka się z mistrzostwem.",
                "hammer",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Meble na wymiar",
                        "Meble na wymiar – Usługa stolarska wykonywana z precyzją i pasją do drewna."),
                    new ServiceData("Kuchnie na wymiar",
                        "Kuchnie na wymiar – Usługa stolarska wykonywana z precyzją i pasją do drewna."),
                    new ServiceData("Szafy i garderoby",
                        "Szafy i garderoby – Usługa stolarska wykonywana z precyzją i pasją do drewna."),
                    new ServiceData("Renowacja mebli",
                        "Renowacja mebli – Usługa stolarska wykonywana z precyzją i pasją do drewna."),
                    new ServiceData("Montaż drzwi",
                        "Montaż drzwi – Usługa stolarska wykonywana z precyzją i pasją do drewna."),
                    new ServiceData("Schody drewniane",
                        "Schody drewniane – Usługa stolarska wykonywana z precyzją i pasją do drewna."),
                    new ServiceData("Altany i pergole",
                        "Altany i pergole – Usługa stolarska wykonywana z precyzją i pasją do drewna."),
                    new ServiceData("Naprawa mebli",
                        "Naprawa mebli – Usługa stolarska wykonywana z precyzją i pasją do drewna.")
                )
            ),
            new CategoryData(
                "Czyszczenie i sprzątanie",
                "czyszczenie",
                "Profesjonalne usługi sprzątające - mieszkania, domy, biura. Czystość na najwyższym poziomie.",
                "sparkles",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Sprzątanie mieszkań",
                        "Sprzątanie mieszkań – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek."),
                    new ServiceData("Sprzątanie po remoncie",
                        "Sprzątanie po remoncie – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek."),
                    new ServiceData("Czyszczenie okien",
                        "Czyszczenie okien – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek."),
                    new ServiceData("Pranie tapicerki",
                        "Pranie tapicerki – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek."),
                    new ServiceData("Czyszczenie dywanów",
                        "Czyszczenie dywanów – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek."),
                    new ServiceData("Sprzątanie biur",
                        "Sprzątanie biur – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek."),
                    new ServiceData("Sprzątanie garaży i piwnic",
                        "Sprzątanie garaży i piwnic – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek."),
                    new ServiceData("Usługi porządkowe",
                        "Usługi porządkowe – Kompleksowa usługa sprzątająca gwarantująca czystość i porządek.")
                )
            ),
            new CategoryData(
                "Transport i przeprowadzki",
                "transport",
                "Bezpieczny transport i profesjonalne przeprowadzki. Twoje rzeczy w dobrych rękach.",
                "truck",
                "https://craftlink-dev.s3.eu-central-1.amazonaws.com/categories/test.jpg",
                List.of(
                    new ServiceData("Przeprowadzki mieszkaniowe",
                        "Przeprowadzki mieszkaniowe – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy."),
                    new ServiceData("Transport mebli",
                        "Transport mebli – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy."),
                    new ServiceData("Przeprowadzki biurowe",
                        "Przeprowadzki biurowe – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy."),
                    new ServiceData("Usługi kurierskie",
                        "Usługi kurierskie – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy."),
                    new ServiceData("Transport budowlany",
                        "Transport budowlany – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy."),
                    new ServiceData("Wynajęm busów z kierowcą",
                        "Wynajęm busów z kierowcą – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy."),
                    new ServiceData("Pakowanie i rozpakowywanie",
                        "Pakowanie i rozpakowywanie – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy."),
                    new ServiceData("Magazynowanie rzeczy",
                        "Magazynowanie rzeczy – Usługa transportowa zapewniająca bezpieczne i sprawne przewiezienie rzeczy.")
                )
            )
        );
    }


    private record CategoryData(
        String name,
        String slug,
        String description,
        String iconName,
        String imageKey,
        List<ServiceData> services
    ) {

    }

    private record ServiceData(
        String name,
        String description
    ) {

    }
}