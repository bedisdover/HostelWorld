/**
 * Created by song on 17-3-22.
 *
 * hostel账户
 */

$(document).ready(function () {
    HostelAccount.init();

    $('#edit').on('click', function () {
        HostelAccount.showEditable();
    });

    $('#confirm').on('click', function () {
        HostelAccount.confirmEdit();
    });
});

var HostelAccount = {
    account: {},
    init: function () {
        $.ajax({
            url: '/hostel/info',
            type: 'post',
            success: function (data) {
                console.log(data);
                if (data.status) {
                    HostelAccount.account = Object.assign({}, data.object);

                    HostelAccount.showAccount();
                }
            }
        })
    },
    showAccount: function () {
        $('#name').html(HostelAccount.account.name);
        $('#address').html(HostelAccount.account.address);
    },
    showEditable: function () {
        $('#edit').addClass('disabled');
        $('#confirm').removeClass('hide');

        $('#name').html('<input type="text" id="name-input" value="' + HostelAccount.account.name + '">');
        $('#address').html('<input type="text" id="address-input" value="' + HostelAccount.account.address + '">')
    },
    confirmEdit: function () {
        $.ajax({
            url: '/hostel/info/edit',
            type: 'post',
            data: {
                name: $('#name-input').val(),
                address: $('#address-input').val()
            },
            success: function (data) {
                Materialize.toast(data.info, 3000);

                HostelAccount.recover();
            }
        })
    },
    recover: function () {
        $('#edit').removeClass('disabled');
        $('#confirm').addClass('hide');

        $('#name').html(HostelAccount.account.name);
        $('#address').html(HostelAccount.account.address);
    }
};
