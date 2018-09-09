$(document).ready(function(){

    $('#doencas').formSelect();


    $('#doencas').change(function () {
        $(this).formSelect();
    });

    $('#limpar').click(function () {
        $('#fotos').val('');
        $('#btnCadastrar').prop("disabled", false);
        $('#aviso').prop("class", "");
    });


    $('#fotos').change(function () {
       var tamanho = this.files;

       if(tamanho.length > 6){
           $('#btnCadastrar').prop("disabled", true);
           $('#aviso').prop("class", "red-text");

       }else{
           $('#btnCadastrar').prop("disabled", false);
           $('#aviso').prop("class", "");
       }
    });


    $('#btnNovaSolicitacao').click(function () {
        carregarUltimaPlantaSolicitada();
    })
});


function carregarUltimaPlantaSolicitada() {

    if ($('#ultimaplanta').text() != ''){
        $('#cultura option:contains(' + $('#ultimaplanta').text() + ')').attr('selected', 'selected');
        $('#cultura').formSelect();
    }
}