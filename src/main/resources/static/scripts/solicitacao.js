$(document).ready(function(){

    $('#doencas').formSelect();

    $('#doencas').change(function () {
        $(this).formSelect();
    });

    $('#fotos').change(function () {
       var tamanho = $(this).val();

       if(tamanho.length > 6){
           console.log("Maior que 6 arquivos");

       }else{
           if($('#btnCadastrar').prop(disabled) == "disabled"){
               $('#btnCadastrar').removeProp("disabled");
           }
       }
    });

    $('#btnNovaSolicitacao').click(function () {
        carregarUltimaPlantaSolicitada();
    })
});


function carregarUltimaPlantaSolicitada() {

    if ($('#ultimaplanta').text() != null){
        $('#cultura option:contains(' + $('#ultimaplanta').text() + ')').attr('selected', 'selected');
        $('#cultura').formSelect();
    }
}