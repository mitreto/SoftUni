package com.example.demo.service.impl;

import com.example.demo.model.entity.Item;
import com.example.demo.model.service.ItemServiceModel;
import com.example.demo.model.view.ItemViewModel;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addItem(ItemServiceModel itemServiceModel) {

        Item item = this.modelMapper.map(itemServiceModel, Item.class);

        item.setCategory(this.categoryService.find(itemServiceModel.getCategory().getCategoryName()));

        this.itemRepository.saveAndFlush(item);
    }

    @Override
    public List<ItemViewModel> findAllItems() {

        return this.itemRepository
                .findAll()
                .stream()
                .map(item -> {
                    ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);

                    itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg",
                            item.getGender().name(),
                            item.getCategory().getCategoryName().name()));

                    return itemViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public ItemViewModel findById(String id) {

        return this.itemRepository.findById(id).map(item -> {
            ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);

            itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg",
                    item.getGender().name(),
                    item.getCategory().getCategoryName().name()));

            return itemViewModel;
        }).orElse(null);
    }

    @Override
    public void delete(String id) {
        this.itemRepository.deleteById(id);
    }
}
