package com.example.demo.service;

import com.example.demo.model.service.ItemServiceModel;
import com.example.demo.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    void addItem(ItemServiceModel itemServiceModel);

    List<ItemViewModel> findAllItems();

    ItemViewModel findById(String id);

    void delete(String id);

}
