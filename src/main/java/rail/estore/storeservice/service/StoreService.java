package rail.estore.storeservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rail.estore.storeservice.dto.RequestStoreDto;
import rail.estore.storeservice.dto.ResponseStoreDto;
import rail.estore.storeservice.exception.AlreadyExistsException;
import rail.estore.storeservice.exception.NotFoundException;
import rail.estore.storeservice.mapper.StoreMapper;
import rail.estore.storeservice.model.Store;
import rail.estore.storeservice.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;


    public List<ResponseStoreDto> findAll() {
        return storeRepository.findAll().stream()
                .map(storeMapper::mapToDto).toList();
    }

    public ResponseStoreDto findById(Long id) {
        return storeRepository.findById(id).map(storeMapper::mapToDto)
                .orElseThrow(() -> new NotFoundException("Store not found"));
    }

    @Transactional
    public ResponseStoreDto save(RequestStoreDto requestStoreDto) {

        storeRepository.findByName(requestStoreDto.name())
                .ifPresent(s -> {
                    throw new AlreadyExistsException(s.getName() + "already exists");
                });

        Store store = storeMapper.mapToObject(requestStoreDto);
        Store savedStore = storeRepository.save(store);

        return storeMapper.mapToDto(savedStore);

    }

    @Transactional
    public ResponseStoreDto update(Long id, RequestStoreDto requestStoreDto) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Store not found"));

        store.setName(requestStoreDto.name());
        store.setLogo(requestStoreDto.logo());
        store.setDescription(requestStoreDto.description());

        Store updatedStore = storeRepository.save(store);

        return storeMapper.mapToDto(updatedStore);
    }

    @Transactional
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }

}
