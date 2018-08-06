$(document).ready(function () {

    // inicializar navBar Mobile
    $('.sidenav').sidenav();

    // inicializar text area ativo
    $('.input-field label').addClass('active');

    // inicializar tag select
    $('#estados').formSelect();
    $('#cidades').formSelect();
    $('#cultura').formSelect();


    // quando houver uma mudança no select de estados...
    $('#estados').change(function(event) {
        buscarCidades();
    });

    $("#cidades").removeAttr('disabled', 'disabled');

    // efetua a busca de estados da federação
    buscarEstados();


});


// Função para buscar os estados
function buscarEstados() {

    var requisicaoEstados = new XMLHttpRequest();
    var tipo = 'GET';
    var assincrona = true;

    // API do IBGE - retorna arquivo JSON com todos os estados da federação
    requisicaoEstados.open(tipo, 'https://servicodados.ibge.gov.br/api/v1/localidades/estados', assincrona);
    requisicaoEstados.send();

    requisicaoEstados.onreadystatechange = function () {
        if(requisicaoEstados.readyState == XMLHttpRequest.DONE && requisicaoEstados.status == 200) {

            var obj = JSON.parse(requisicaoEstados.responseText);

            // função para ordenar os objetos por ordem alfabética
            obj.sort(function(a,b) {
                if(a.nome < b.nome) return -1;
                if(a.nome > b.nome) return 1;
                return 0;
            });

            // variável que será incremetada com <option>
            var option = "";

            for(var i=0; i < obj.length; i++){
                if(obj[i] != null) {
                    option += '<option value="' + obj[i].nome + '" id="' + obj[i].id + '">' + obj[i].nome + '</option>';
                }
            }

            option += "</option>";

            // acrescenta em html as tags <option>
            $("#estados").html(option);

            $('#estados').formSelect();

            if ($('#ultimoestado').text() != null){
                $('#estados option:contains(' + $('#ultimoestado').text() + ')').attr('selected', 'selected');
                $('#estados').formSelect();
                buscarCidades();
            }



            // habilita o select cidades
            $("#cidades").removeAttr('disabled');
        }
    }
}

// Função para buscar as cidades dependendo do estado escolhido
function buscarCidades(){

    var requisicaoCidades = new XMLHttpRequest();
    var tipo = 'GET';
    var assincrona = true;
    var idEstado = $('#estados').find(':selected').attr('id');

    // API do IBGE que retorna todos os municipios relacionados com o id do estado
    requisicaoCidades.open(tipo, 'https://servicodados.ibge.gov.br/api/v1/localidades/estados/' + idEstado + '/municipios' ,assincrona);
    requisicaoCidades.send();

    requisicaoCidades.onreadystatechange = function () {
        if(requisicaoCidades.readyState == XMLHttpRequest.DONE && requisicaoCidades.status == 200) {

            var obj = JSON.parse(requisicaoCidades.responseText);

            // função para ordenar os objetos por ordem alfabética
            obj.sort(function(a,b) {
                if(a.nome < b.nome) return -1;
                if(a.nome > b.nome) return 1;
                return 0;
            });

            var option = "";



            for(var i=0; i < obj.length; i++){
                option+= '<option value="' + obj[i].nome+ '" id="' + obj[i].id + '">' + obj[i].nome + '</option>';
            }

            // acrescenta em html as tags <option>
            $("#cidades").html(option);


            $('#cidades').formSelect();


            if ($('#ultimacidade').text() != null){
                $('#cidades option:contains(' + $('#ultimacidade').text() +')').attr('selected', 'selected');
                $('#cidades').formSelect();
            }

        }
    }
}

