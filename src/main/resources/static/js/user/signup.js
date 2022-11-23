'use strict';

jQuery(function($) {
    $("#btn-signup").click(function(date) {
        signupUser();
    });

    function signupUser() {
        removeValidResult();
        var formData = $('#signup-form').serializeArray();

        $.ajax({
           type: "POST",
           cache: false,
           url: '/user/signup/rest',
           data: formData,
           dataType: 'json',

        }).done(function(data) {
           console.log(data);
           if (data.result === 90) {
              $.each(data.error, function(key, value) {
                 reflectValidResult(key, value);
              });
           } else if (data.result === 0 ) {
              alert('success register user');
              window.location.href = '/login';
           }

        }).fail(function(jqXHR, textStatus, errorThrown) {
           alert('failed register user');

        }).always(function() {});
    }

    function removeValidResult() {
       $('.is-invalid').removeClass('is-invalid');
       $('.invalid-feedback').remove();
       $('.text-danger').remove();
    }

    function reflectValidResult(key, value) {
       if (key === 'gender') {
          $('input[name=' + key + ']').addClass('is-invalid');
          $('input[name=' + key + ']').parent().parent().append('<div class="text-danger">' + value + '</div>');
       } else {
          $('input[id=' + key + ']').addClass('is-invalid');
          $('input[id=' + key + ']').parent().parent().append('<div class="text-danger">' + value + '</div>');
       }
    }
});