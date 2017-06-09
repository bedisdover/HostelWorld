/**
 * Created by song on 17-3-21.
 *
 * 账户
 */

$(document).ready(function () {
    Account.init();

    $('#edit').on('click', function () {
        Account.showEdit();
    });

    $('#confirm').on('click', function () {
        Account.edit();
    });

    $('.modal').modal({
        complete: function () {
            Account.recharge();
        }
    });
});

var Account = {
    init: function () {
        User.getInfo(Account.showInfo);
    },
    showInfo: function () {
        $('#name').html(User.user.name);
        $('#cardNum').html(User.user.cardNum);
        $('#bankCard').html(User.user.bankCard);
        $('#phoneNum').html(User.user.phoneNum);
        $('#rate').html(User.user.rate);
        $('#sum').html(User.user.sum);
        $('#discount').html(User.user.discount);
        $('#balance').html(User.user.balance);

        $('#edit').removeClass('disabled');
        $('#confirm').addClass('hide');
    },
    // 显示编辑状态
    showEdit: function () {
        $('#name').html('<input type="text" id="name-input" value="' + User.user.name + '">');
        $('#bankCard').html('<input type="text" id="bankCard-input" value="' + User.user.bankCard + '">');
        $('#phoneNum').html('<input type="text" id="phoneNum-input" value="' + User.user.phoneNum + '">');

        $('#edit').addClass('disabled');
        $('#confirm').removeClass('hide');
    },
    edit: function () {
        $.ajax({
            url: '/info/edit',
            type: 'post',
            data: {
                name: $('#name-input').val(),
                bankCard: $('#bankCard-input').val(),
                phoneNum: $('#phoneNum-input').val(),
                password: User.user.password
            },
            success: function (data) {
                if (data.status) {
                    Account.init();
                } else {
                    Materialize.toast("出错了", 3000);
                }
            }
        })
    },
    recharge: function () {
        var amount = parseFloat($('#recharge-amount').val());
        $.ajax({
            url: '/recharge',
            type: 'post',
            data: {
                amount: amount
            },
            success: function (data) {
                Materialize.toast(data.info, 3000);

                Account.init();
            }
        })
    }
};
