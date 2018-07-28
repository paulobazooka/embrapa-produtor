$(document).ready(function(){

    // inicializar navBar Mobile
    $('.sidenav').sidenav();

    // inicialização do botão add
    $('.fixed-action-btn').floatingActionButton({
        direction: 'right',
        hoverEnabled: true,
        toolbarEnabled: false
    });

    $('.tooltipped').tooltip();

    $('#cultura').formSelect();

    $('#do').formSelect();

    $('.modal').modal({
        dismissible: false,
    });

});
