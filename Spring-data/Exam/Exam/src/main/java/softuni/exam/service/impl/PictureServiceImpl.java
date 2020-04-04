package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.exam.models.dtos.PictureSeedDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static softuni.exam.constants.GlobalConstants.*;


@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final CarService carService;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, CarService carService) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.carService = carService;
    }


    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Paths.get(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder resultInfo = new StringBuilder();

        PictureSeedDto[] pictureSeedDtos = this.gson
                .fromJson(new FileReader(PICTURES_FILE_PATH), PictureSeedDto[].class);

        Arrays.stream(pictureSeedDtos)
                .forEach(pictureSeedDto -> {

                    if (this.validationUtil.isValid(pictureSeedDto)){

                        if (this.pictureRepository.findByName(pictureSeedDto.getName()) == null){

                            Picture picture = this.modelMapper.map(pictureSeedDto, Picture.class);

                            LocalDateTime localDateTime =
                                    LocalDateTime.parse(pictureSeedDto.getDateAndTime(),
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                            picture.setDateAndTime(localDateTime);

                            Car car = this.carService.getById(pictureSeedDto.getCar());

                            if (car == null){
                                resultInfo.append("No such car in DB");
                                return;
                            }

                            picture.setCar(car);

                            this.pictureRepository.saveAndFlush(picture);

                            resultInfo.append(
                                    String.format(SUCCESSFUL_IMPORT_PICTURE_MESSAGE, picture.getName()));


                        } else {
                            resultInfo.append(ALREADY_IN_DB);
                        }

                    } else {
                        resultInfo.append(INVALID_PICTURE);

                    }
                    resultInfo.append(System.lineSeparator());
                });


        return resultInfo.toString();
    }
}
