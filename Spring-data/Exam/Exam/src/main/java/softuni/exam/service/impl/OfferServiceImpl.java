package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.OfferSeedRootDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final CarService carService;
    private final SellerService sellerService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, CarService carService, SellerService sellerService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.sellerService = sellerService;
    }


    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Paths.get(OFFERS_FILE_PATH));

    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder resultInfo = new StringBuilder();

        OfferSeedRootDto offerSeedRootDto = this.xmlParser
                .importFromXml(OfferSeedRootDto.class, OFFERS_FILE_PATH);

        offerSeedRootDto.getOffers()
                .forEach(offerSeedDto -> {

                    if (this.validationUtil.isValid(offerSeedDto)) {

                        LocalDateTime addedOn =
                                LocalDateTime.parse(offerSeedDto.getAddedOn(),
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        if (this.offerRepository
                                .findByDescriptionAndAddedOn(
                                        offerSeedDto.getDescription(), addedOn) == null) {

                            Offer offer = this.modelMapper.map(offerSeedDto, Offer.class);

                            offer.setAddedOn(addedOn);

                            Car car = this.carService.getById(offerSeedDto.getCar().getId());

                            if (car == null){
                                resultInfo.append(INVALID_CAR).append(System.lineSeparator());
                                return;

                            } else {
                                offer.setCar(car);
                            }

                            Seller seller = this.sellerService.getById(offerSeedDto.getSeller().getId());

                            if (seller == null){
                                resultInfo.append(INVALID_SELLER).append(System.lineSeparator());
                                return;

                            } else {
                                offer.setSeller(seller);
                            }

                            this.offerRepository.saveAndFlush(offer);

                            resultInfo.append(
                                    String.format(SUCCESSFUL_IMPORT_OFFER_MESSAGE,
                                            offer.getAddedOn(),
                                            offer.isHasGoldStatus()));

                        } else {
                            resultInfo.append(ALREADY_IN_DB);
                        }


                    } else {
                        resultInfo.append(INVALID_OFFER);
                    }

                    resultInfo.append(System.lineSeparator());
                });

        return resultInfo.toString();
    }
}
