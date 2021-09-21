package uni.ami.restdb.serviseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
import uni.ami.restdb.model.*;
import uni.ami.restdb.repository.CarRepository;
import uni.ami.restdb.repository.SeatRepository;
import uni.ami.restdb.repository.SeatTypeRepository;
import uni.ami.restdb.service.SeatService;

import java.util.List;

/**
 * Класс сервиса мест
 * @author damir
 */
@Slf4j
@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    SeatTypeRepository seatTypeRepository;

    /**
     * Функция сохранения места в базе данных
     * @param seat принимает класс места для сохранения {@link Seat}
     * @param id принимает id вагона для сохранения в нем места
     * @return возвращает сохраненнре место {@link Seat}
     */
    @Override
    public Seat save(Seat seat, Long id) {
        try {
            Car car = carRepository.findById(id).orElseThrow(FindException::new);
            seat.setCar(car);
            seat.setCId(car.getId());
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Вагона с заданным id не найдено!");
        }

        try {
            SeatType seatType = seatTypeRepository.findByNameEquals(seat.getSeatType());
            seat.setType(seatType);
            seat.setSeatType(seatType.getName());
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Такого типа места не существует!");
        }

        return seatRepository.save(seat);
    }

    /**
     * Функция удаления места из базы данных
     * @param id принимет в качестве параметра id места
     * @return возвращает HttpStatus.OK {@link ResponseEntity}
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        Seat seat = getSeatById(id);
        seatRepository.delete(seat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Функция изменения информации о месте в базе данных
     * @param id принимет в качестве параметра id места
     * @param seat принимает класс места для изменения информации {@link Seat}
     * @return возвращает класс места с измененной информацией {@link Seat}
     */
    @Override
    public Seat update(Long id, Seat seat) {
        return seatRepository.findById(id)
                .map(seat_temp -> {
                    if (seat.getCId() != null) {
                        try {
                            Car car = carRepository.findById(id).orElseThrow(FindException::new);
                            seat.setCar(car);
                            seat.setCId(car.getId());
                        } catch (NullPointerException e) {
                            throw new ResourceNotFoundException("Вагона с заданным id не найдено!");
                        }
                    }

                    if (seat.getSeatType() != null) {
                        try {
                            SeatType seatType = seatTypeRepository.findByNameEquals(seat.getSeatType());
                            seat.setType(seatType);
                            seat.setSeatType(seatType.getName());
                        } catch (NullPointerException e) {
                            throw new ResourceNotFoundException("Такого типа места не существует!");
                        }
                    }

                    if (seat.getCost() != null) {
                        seat_temp.setCost(seat.getCost());
                    }
                    if (seat.getNumber() != null) {
                        seat_temp.setNumber(seat.getNumber());
                    }

                    return seatRepository.save(seat_temp);
                }).orElseThrow(() -> new ResourceNotFoundException("Места с данным id не найдено!"));
    }

    /**
     * Функция поиска места по id
     * @param id принимет в качестве параметра id места
     * @return озвращает класс места {@link Seat}
     */
    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Места с данным id не найдено!"));
    }

    /**
     * Функция поиска всех мест в базе данных
     * @param pageable заглушка
     * @return возвращает список всех мест в формате Pageable {@link Pageable}
     */
    @Override
    public Page<Seat> getAll(Pageable pageable) {
        return seatRepository.findAll(pageable);
    }

    /**
     * Функция поиска всех мест по id вагона
     * @param id принимает id вагона
     * @return возвращает список мест
     */
    @Override
    public List<Seat> findAllByCarId(Long id) {
        return seatRepository.findAllByCarIdEquals(id);
    }

    /**
     * Функция поиска всех мест по id билета
     * @param id принимает id билета
     * @return возвращает список мест
     */
    @Override
    public List<Seat> findAllByTicketId(Long id) {
        return seatRepository.findAllByTicketIdEquals(id);
    }

    /**
     * Функция поиска всех мест по стоимости
     * @param cost параметр стоимости для поиска
     * @return возвращает список мест
     */
    @Override
    public List<Seat> findAllByCost(Integer cost) {
        return seatRepository.findAllByCostEquals(cost);
    }
}
