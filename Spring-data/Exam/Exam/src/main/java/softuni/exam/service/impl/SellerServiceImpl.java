package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.SellerSeedRootDto;
import softuni.exam.models.entities.Rating;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Paths.get(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder resultInfo = new StringBuilder();

        SellerSeedRootDto sellerSeedRootDto =
                this.xmlParser.importFromXml(SellerSeedRootDto.class, SELLERS_FILE_PATH);

        sellerSeedRootDto.getSellers()
                .forEach(sellerSeedDto -> {

                    if (validationUtil.isValid(sellerSeedDto)) {

                        if (this.sellerRepository.findByEmail(sellerSeedDto.getEmail()) == null) {

                            Seller seller = this.modelMapper.map(sellerSeedDto, Seller.class);

                            try {
                                Rating rating = Rating.valueOf(sellerSeedDto.getRating());

                            } catch (IllegalArgumentException ex) {
                                resultInfo.append(INVALID_SELLER).append(System.lineSeparator());
                                return;
                            }

                            seller.setRating(Rating.valueOf(sellerSeedDto.getRating()));

                            this.sellerRepository.saveAndFlush(seller);

                            resultInfo.append(
                                    String.format(SUCCESSFUL_IMPORT_SELLER_MESSAGE,
                                            seller.getLastName(),
                                            seller.getEmail()));

                        } else {
                            resultInfo.append(ALREADY_IN_DB);
                        }


                    } else {
                        resultInfo.append(INVALID_SELLER);
                    }
                    resultInfo.append(System.lineSeparator());
                });

        return resultInfo.toString();
    }

    @Override
    public Seller getById(Long id) {
        return this.sellerRepository.findById(id).orElse(null);
    }
}
