$(document).ready(function () {
    $('.modal').modal({
        dismissible: false,
    });

    $('#opcao').change(function () {
        if($("#opcao").is(':checked')){
            $('#botaoApagar').prop("disabled", false);
        }else{
            $('#botaoApagar').prop("disabled", true);
        }
    });

    $('#botaoAlterar').click(function () {
        $('#botaoConfirmar').prop("disabled", false);
        $('#nome').prop("disabled", false);
        $('#email').prop("disabled", false);
        $('#telefone').prop("disabled", false);
    })

    $('#botaoAlterarSenha').click(function () {
        $('#botaoConfirmarSenha').prop("disabled", false);
        $('#senha').prop("disabled", false);
        $('#confirma').prop("disabled", false);
    })

});
