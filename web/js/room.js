/**
 * Created by song on 17-3-16.
 *
 * 房间信息
 */

var Room = {
    getAllHostel: function (callback) {
        $.ajax({
            url: '/room/all',
            type: 'post',
            success: function (data) {
                if (data.status) {
                    callback(data.object);
                }
            }
        })
    },
    getAvailableRoom: function (hostelID, callback) {
        $.ajax({
            url: '/room/available',
            type: 'post',
            data: {
                hostelID: hostelID
            },
            success: function (data) {
                if (data.status) {
                    callback(data.object);
                }
            }
        })
    },
    getAvailableRoomByDate: function (hostelID, startDate, endDate, callback) {
        $.ajax({
            url: '/room/available/date',
            type: 'post',
            data: {
                hostelID: hostelID,
                startDate: startDate,
                endDate: endDate
            },
            success: function (data) {
                if (data.status) {
                    callback(data.object);
                }
            }
        })
    }
};