package com.restoran.restoran;

import com.restoran.restoran.model.Masa;
import com.restoran.restoran.model.Yemek;
import com.restoran.restoran.repository.MasaRepository;
import com.restoran.restoran.repository.YemekRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestoranApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoranApplication.class, args);
	}

	@Bean
	CommandLineRunner init(MasaRepository masaRepository, YemekRepository yemekRepository) {
		return args -> {
			if (masaRepository.count() == 0) {
				String[] salonlar = {"A1", "A2", "A3", "A4", "A5"};
				for (String no : salonlar) {
					Masa m = new Masa();
					m.setMasaNo(no);
					m.setBolge("SALON");
					m.setDolu(false);
					masaRepository.save(m);
				}
				String[] bahceler = {"B1", "B2", "B3", "B4", "B5"};
				for (String no : bahceler) {
					Masa m = new Masa();
					m.setMasaNo(no);
					m.setBolge("BAHCE");
					m.setDolu(false);
					masaRepository.save(m);
				}
			}

			if (yemekRepository.count() == 0) {
				Object[][] yemekler = {
						{"Türk Kahvesi", "Sıcak İçecekler", 75.0},
						{"Cappuccino", "Sıcak İçecekler", 120.0},
						{"Latte", "Sıcak İçecekler", 130.0},
						{"Espresso", "Sıcak İçecekler", 90.0},
						{"Çay", "Sıcak İçecekler", 40.0},
						{"Soğuk Kahve", "Soğuk İçecekler", 140.0},
						{"Limonata", "Soğuk İçecekler", 95.0},
						{"Ayran", "Soğuk İçecekler", 45.0},
						{"Meyve Suyu", "Soğuk İçecekler", 80.0},
						{"Su", "Soğuk İçecekler", 30.0},
						{"Tost", "Yiyecekler", 120.0},
						{"Sandviç", "Yiyecekler", 150.0},
						{"Krep", "Yiyecekler", 160.0},
						{"Waffle", "Yiyecekler", 175.0},
						{"Kumpir", "Yiyecekler", 200.0},
						{"Cheesecake", "Tatlılar", 180.0},
						{"Brownie", "Tatlılar", 150.0},
						{"Tiramisu", "Tatlılar", 190.0},
						{"Profiterol", "Tatlılar", 165.0},
						{"Dondurma", "Tatlılar", 130.0}
				};
				for (Object[] y : yemekler) {
					Yemek yemek = new Yemek();
					yemek.setAd((String) y[0]);
					yemek.setKategori((String) y[1]);
					yemek.setFiyat((Double) y[2]);
					yemekRepository.save(yemek);
				}
			}
		};
	}
}