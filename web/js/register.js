/**
 * Created by song on 17-3-15.
 *
 * 注册
 */

$(document).ready(function () {
    $('label').attr('data-error', function () {
        if ($(this).attr('for') !== 'password-repeat') {
            return $(this).html() + '不能为空';
        } else {
            return '密码不一致';
        }
    });

    $('#successModal').modal({
        dismissible: false
    });
});

(function () {
    $('#form-wrapper').on('keydown', function (e) {
        if ($('#register').hasClass('disabled')) {
            return;
        }

        if (e.which == 13) {
            Register.register();
        }
    });

    $('#register').on('click', function () {
        Register.register();
    })
})();

var Register = {
    path: top.location.pathname,
    url: '',
    urlMapping: {
        user: '/register',
        hostel: '/hostel/register'
    },
    data: {},
    init: function () {
        if (this.path === '/html/register.html') {
            this.url = this.urlMapping.user;

            this.data = {
                name: $('#name').val().trim(),
                phoneNum: $('#phoneNum').val().trim(),
                bankCard: $('#bankCard').val().trim(),
                password: $('#password').val()
            }
        } else if (this.path === '/html/register-hostel.html') {
            this.url = this.urlMapping.hostel;

            this.data = {
                name: $('#name').val().trim(),
                address: $('#address').val().trim(),
                password: $('#password').val().trim()
            }
        }
    },
    check: function () {
        var isOk = true;

        $('input').not('#password-repeat').filter(function () {
            if ($(this).val().trim() === '') {
                $(this).addClass('invalid');

                isOk = false;
            } else {
                $(this).removeClass('invalid');
            }
        });

        var passwordRepeat = $('#password-repeat');
        if ($('#password').val() !== $(passwordRepeat).val()) {
            isOk = false;
            $(passwordRepeat).addClass('invalid');
        } else {
            $(passwordRepeat).removeClass('invalid');
        }

        return isOk;
    },
    register: function () {
        if (this.check()) {
            $('#register').addClass('disabled');

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
                    $('#cardNum').html(data.object.toString());

                    $('#successModal').modal('open');
                } else {
                    Materialize.toast(data.info, 3000, '', function () {
                        $('#register').removeClass('disabled');
                    })
                }
            }
        })
    }
};
