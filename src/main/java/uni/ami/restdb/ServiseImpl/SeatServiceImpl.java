package uni.ami.restdb.ServiseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.Seat;
import uni.ami.restdb.model.SeatType;
import uni.ami.restdb.repository.CarRepository;
import uni.ami.restdb.repository.SeatRepository;
import uni.ami.restdb.repository.SeatTypeRepository;
import uni.ami.restdb.service.SeatService;

import java.util.List;

@Slf4j //TODO: lombok
@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    SeatTypeRepository seatTypeRepository;

    @Override
    public Seat save(Seat seat, Long id) {
        Car car = carRepository.findById(id).orElseThrow(FindException::new);
        seat.setCar(car);
        seat.setCId(car.getId());

        SeatType seatType = seatTypeRepository.findByNameEquals(seat.getSeatType());
        seat.setType(seatType);
        seat.setSeatType(seatType.getName());

        return seatRepository.save(seat);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Seat seat = getSeatById(id);
        seatRepository.delete(seat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public Seat update(Long id, Seat seat) {
        return seatRepository.findById(id)
                .map(seat_temp -> {
                    if (seat.getCId() != null) {
                        Car car = carRepository.findById(seat.getCId()).orElseThrow(FindException::new);
                        seat_temp.setCar(car);
                        seat_temp.setCId(car.getId());
                    }

                    if (seat.getSeatType() != null) {
                        SeatType seatType = seatTypeRepository.findByNameEquals(seat.getSeatType());
                        seat_temp.setType(seatType);
                        seat_temp.setSeatType(seatType.getName());
                    }

                    if (seat.getCost() != null) {
                        seat_temp.setCost(seat.getCost());
                    }
                    if (seat.getNumber() != null) {
                        seat_temp.setNumber(seat.getNumber());
                    }

                    return seatRepository.save(seat_temp);
                }).orElseThrow(FindException::new);
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public Page<Seat> getAll(Pageable pageable) {
        return seatRepository.findAll(pageable);
    }

    @Override
    public List<Seat> findAllByCarId(Long id) {
        return seatRepository.findAllByCarIdEquals(id);
    }

    @Override
    public List<Seat> findAllByTicketId(Long id) {
        return seatRepository.findAllByTicketIdEquals(id);
    }

    @Override
    public List<Seat> findAllByCost(Integer cost) {
        return seatRepository.findAllByCostEquals(cost);
    }
}
