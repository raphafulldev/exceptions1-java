package model.entities;

import model.entities.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkIn;
    private Date checkout;

    public Reservation() {
    }

    public Reservation(Date checkout, Date checkIn, Integer roomNumber) {
        if (!checkout.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkout = checkout;
        this.checkIn = checkIn;
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckout() {
        return checkout;
    }

    //Usa a classe TimeUnit para converter de milisegundos para dias.
    public long duration() {
        long diff = checkout.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date checkout) {
        Date now = new Date();
        if (checkIn.before(now) || checkout.before(now)) {
            throw new DomainException("Reservation dates update must be future dates");
        }
        if (!checkout.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }

        this.checkIn = checkIn;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkout) + ", " + duration() + " nights";
    }
}
