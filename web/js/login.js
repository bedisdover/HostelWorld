/**
 * Created by song on 17-3-15.
 *
 * 登录
 */

$(document).ready(function () {
    $('label').attr('data-error', function () {
        return $(this).html() + '不能为空';
    });
});

(function () {
    $('#form-wrapper').on('keydown', function (e) {
        if ($('#login').hasClass('disabled')) {
            return;
        }

        if (e.which == 13) {
            Login.login();
        }
    });

    $('#login').on('click', function () {
        Login.login();
    });
})();

var Login = {
    path: top.location.pathname,
    url: '',
    urlMapping: {
        user: '/login',
        hostel: '/hostel/login',
        manager: '/hostel/manager/login'
    },
    // 登录成功后跳转的页面
    page: {
        user: 'hostel-list.html',
        hostel: 'hostel.html',
        manager: 'manager.html'
    },
    data: {},
    init: function () {
        this.data.password = $('#password').val().trim();

        if (this.path === '/html/login.html') {
            this.url = this.urlMapping.user;

            this.data.cardNum = $('#cardNum').val().trim();
        } else if (this.path === '/html/login-hostel.html') {
            this.url = this.urlMapping.hostel;

            this.data.hostelID = $('#hostelID').val().trim();
        } else if (this.path === '/html/login-manager.html') {
            this.url = this.urlMapping.manager;

            this.data.name = $('#name').val().trim();
        }
    },
    check: function () {
        var isOk = true;

        $('input').filter(function () {
            if ($(this).val() === '') {
                isOk = false;

                $(this).addClass('invalid');
            } else {
                $(this).removeClass('invalid');
            }
        });

        return isOk;
    },
    login: function () {
        if (this.check()) {
            $('#login').addClass('disabled');

            this.init();
            this.send();
        }
    },
    send: function () {
        $.ajax({
            url: this.url,
            type: 'post',
            data: this.data,
            success: function (data) {
                if (data.status) {
                    if (this.path === '/html/login.html') {
                        top.location = this.page.user;
                    } else if (this.path === '/html/login-hostel.html') {
                        top.location = this.page.hostel;
                    } else if (this.path === '/html/login-manager.html') {
                        top.location = this.page.manager;
                    }
                } else {
                    Materialize.toast(data.info, 3000, '', function () {
                        $('#login').removeClass('disabled');
                    });
                }
            }.bind(this)
        })
    }
};