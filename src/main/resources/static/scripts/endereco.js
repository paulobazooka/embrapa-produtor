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


    var option = "";
    option += '<option id="12">Acre</option>';
    option += '<option id="27">Alagoas</option>';
    option += '<option id="16">Amapá</option>';
    option += '<option id="13">Amazonas</option>';
    option += '<option id="29">Bahia</option>';
    option += '<option id="23">Ceará</option>';
    option += '<option id="53">Distrito Federal</option>';
    option += '<option id="32">Espírito Santo</option>';
    option += '<option id="52">Goiás</option>';
    option += '<option id="21">Maranhão</option>';
    option += '<option id="51">Mato Grosso</option>';
    option += '<option id="50">Mato Grosso do Sul</option>';
    option += '<option id="31">Minas Gerais</option>';
    option += '<option id="15">Pará</option>';
    option += '<option id="25">Paraíba</option>';
    option += '<option id="41">Paraná</option>';
    option += '<option id="26">Pernambuco</option>';
    option += '<option id="22">Piauí</option>';
    option += '<option id="34">Rio de Janeiro</option>';
    option += '<option id="24">Rio Grande do Norte</option>';
    option += '<option id="43">Rio Grande do Sul</option>';
    option += '<option id="11">Rondônia</option>';
    option += '<option id="14">Roraima"</option>';
    option += '<option id="42">Santa Catarina</option>';
    option += '<option id="35">São Paulo</option>';
    option += '<option id="28">Sergipe</option>';
    option += '<option id="17">Tocantins</option>';

   /* var requisicaoEstados = new XMLHttpRequest();
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
            }*/

            option += "</option>";

            // acrescenta em html as tags <option>
            $("#estados").html(option);

            $('#estados').formSelect();

            /*if ($('#ultimoestado').text() != null){
                $('#estados option:contains(' + $('#ultimoestado').text() + ')').attr('selected', 'selected');
                $('#estados').formSelect();
                buscarCidades();
            }*/



            // habilita o select cidades
            $("#cidades").removeAttr('disabled');
            buscarCidades();


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

