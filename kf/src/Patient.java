public class Patient {				//病人类
    String checkInTime ;			//入住时间
    String leaveTime ;				//离开时间

    String phone ;					//电话号码
    String name ;					//名字

    String roomNum ;					//房间号
    Patient( String name, String roomNum,String phone, String checkInTime, String leaveTime) {
        this.roomNum = roomNum ;
        this.checkInTime = checkInTime ;
        this.leaveTime = leaveTime ;
        this.phone = phone  ;
        this.name = name  ;

    }
    String getRoomNum() {
        return roomNum ;
    }
    String getName() {
        return name ;
    }
    String getPhone() {
        return phone ;
    }
    String getCheckInTime() {
        return checkInTime ;
    }
    String getLeaveTime() {
        return leaveTime ;
    }



}