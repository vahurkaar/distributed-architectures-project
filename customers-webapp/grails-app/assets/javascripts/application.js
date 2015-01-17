// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self

$(document).ready(function () {

    var showSpinner = function() {
        $("#spinner").fadeIn('fast');
    };

    $('body').on('click', '#addAddressRowButton', function() {
        var rowIndex = $('#addressRows tr').size();
        var emptyRowHtml =
            '<tr id="addressRow' + rowIndex + '" class="addressRow">' +
                '<td>' +
                '   <input id="command.addresses[' + rowIndex + '].addressId" type="hidden" value="" name="command.addresses[' + rowIndex + '].addressId" />' +
                '   <input id="command.addresses[' + rowIndex + '].email" type="text" value="" name="command.addresses[' + rowIndex + '].email" />' +
                '</td>' +
                '<td><input id="command.addresses[' + rowIndex + '].address" type="text" value="" name="command.addresses[' + rowIndex + '].address" /></td>' +
                '<td><input id="command.addresses[' + rowIndex + '].zip" type="text" value="" name="command.addresses[' + rowIndex + '].zip" style="width: 100px" /></td>' +
                '<td><a class="deleteAddressButton" style="cursor: pointer;"><img src="/customers/assets/skin/database_delete.png" /></a></td>' +
            '</tr>';
        console.debug('add new row ' + rowIndex);
        if (rowIndex > 0) {
            $('#addressRows tr.addressRow:last').after(emptyRowHtml);
        } else {
            $('#addressRows').append(emptyRowHtml);
        }
    });

    $('body').on('click', '.deleteAddressButton', function() {
        $(this).closest('tr').remove();
    });

    // Global handlers for AJAX events
    $(document)
        .on("ajaxSend", showSpinner)
        .on("ajaxStop", function() {
            $("#spinner").fadeOut('fast');
        })
        .on("ajaxError", function(event, jqxhr, settings, exception) {
            $("#spinner").hide();
        });
});

function performSearch() {
    $("#customerSearchButton").click();
}