public class Room {		//诊室类
    String type ;		//诊室型号
    String state ;		//当前诊室状态
    int roomNum ;		//诊室号
    Room() {}
    Room(String type, int roomNum, String state) {
        this.type = type ;
        this.roomNum = roomNum ;
        this.state = state ;

    }
    void setState(String state) {	//修改房间状态
        this.state = state ;
    }
    int getRoomNum() {				//返回房号
        return roomNum ;
    }
    String getType() {				//返回房间类型
        return type ;
    }
    String getState() {				//返回房间状态
        return state ;
    }
}