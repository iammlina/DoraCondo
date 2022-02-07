package condo.dora.services;
//ไม่ได้ใช้
public class RoomNumberNotFoundInCondoException extends IllegalArgumentException{
    public RoomNumberNotFoundInCondoException () { super("ไม่มีหมายเลขห้องนี้ในคอนโด");}

    public RoomNumberNotFoundInCondoException (String message) {
        super(message);
    }
}
