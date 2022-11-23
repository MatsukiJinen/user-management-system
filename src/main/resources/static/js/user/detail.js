'use strict';

jQuery(function($) {
  $('#btn-update').click(function(event) {
      updateUser();
  });

  $('#btn-delete').click(function(event) {
      deleteUser();
  });

  function updateUser() {
      var formData = $('#user-detail-form').serializeArray();
      $.ajax({
        type: "PUT",
        cache: false,
        url: '/user/update',
        data: formData,
        dataType: 'json',
      }).done(function(data) {
        alert('success update user');
        window.location.href = '/user/list';
      }).fail(function(jqXHR, textStatus, errorThrown) {
        alert('failed update user');
      }).always(function() {
      });
  }

  function deleteUser() {
     var formData = $('#user-detail-form').serializeArray();
     $.ajax({
       type: "DELETE",
       cache: false,
       url: '/user/delete',
       data: formData,
       dataType: 'json',
     }).done(function(data) {
       alert('success delete user');
       window.location.href = '/user/list';
     }).fail(function(jqXHR, textStatus, errorThrown) {
       alert('failed delete user');
     }).always(function() {
     });
  }
});