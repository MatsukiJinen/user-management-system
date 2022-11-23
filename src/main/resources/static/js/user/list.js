'use strict';

var userData = null;
var table = null;

jQuery(function($) {

    destroyDataTables();

    $('#btn-search').click(function(data) {
        search();
    });

    function search() {
       var formData = $('#user-search-form').serialize();

       $.ajax({
          type: "GET",
          url: '/user/get/list',
          data: formData,
          dataType: 'json',
          contentType: 'application/json; charset=UTF-8',
          timeout: 5000,

       }).done(function(data) {
          console.log(data);
          userData = data;
          createDataTables();

       }).fail(function(jqXHR, textStatus, errorThrown) {
          alert('failed search user');

       }).always(function() {});
    }

    function destroyDataTables() {
         // 既に作成されている場合は、破棄して初期化する
         if (table !== null) {
             table.destroy();
        }
    }

    function createDataTables() {
        table = $('#user-list-table').DataTable({
            data: userData,
            columns: [
               {data: 'userId'},
               {data: 'userName'},
               {
                  data: 'birthday',
                  render: function(data, type, row) {
                     var date = new Date(data);
                     var year = date.getFullYear();
                     var month = date.getFullYear();
                     var date = date.getDate();
                     return year + '/' + month + '/' + date;
                  }
               },
               {data: 'age'},
               {
                  data: 'gender',
                  render: function(data, type, row) {
                     var gender = '';
                     if (data === 1) {
                        gender = 'Male';
                     } else {
                        gender = 'FeMale';
                     }
                     return gender;
                  }
               },
               {
                  data: 'userId',
                  render: function(data, type, row) {
                     var url = '<a href="detail/' + data +'">詳細</a>';
                     return url;
                  }
               }
            ]
        });
    }


});