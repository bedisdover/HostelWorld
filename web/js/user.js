/**
 * Created by song on 17-3-18.
 *
 * user相关
 */

$(document).ready(function () {
    User.getInfo();
});

var User = {
    user: {},
    getInfo: function (callback) {
        $.ajax({
            url: '/isLogin',
            type: 'post',
            success: function (data) {
                if (data.status) {
                    User.user = Object.assign({}, data.object);
                }

                if (callback !== undefined) {
                    callback(User.user);
                }
            }
        })
    }
};
