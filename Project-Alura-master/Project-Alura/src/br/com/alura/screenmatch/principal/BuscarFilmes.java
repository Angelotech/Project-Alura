package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class BuscarFilmes {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner entrada =  new Scanner(System.in);
        System.out.print("digite o nome do filme: ");
        var buscar  = entrada.nextLine();

        String endereco = ("https://www.omdbapi.com/?t=" + buscar + "&apikey=bbc3db2");

        // 1. Criando o cliente HTTP
        // Esse é o "objeto" que vai enviar a requisição e receber a resposta
        HttpClient client = HttpClient.newHttpClient();

        // 2. Criando a requisição HTTP
        // newBuilder() inicia a construção do request
        // .uri(...) define para onde vamos enviar (o endereço do servidor)
        // .GET() indica que vamos buscar informações (metodo HTTP GET)
        // .build() finaliza a criação do request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco)) /* -> aqui fica a url da Api  */
                .GET()
                .build();

        // 3. Enviando a requisição e recebendo a resposta
        // client.send(...) envia o request para o servidor
        // HttpResponse<String> significa que queremos receber o corpo como String
        // BodyHandlers.ofString() converte a resposta para texto
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        String RespostaDoCorpo = response.body();

        // System.out.print(response.body());

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create(); // isso faz com as requição dos campos title entre outras fiquem maiusculas
        TituloOmdb meuTituloOmdb = gson.fromJson(RespostaDoCorpo, TituloOmdb.class); // resposta do corpo + classe que qeuremos tranforma o mesmo
        System.out.println(meuTituloOmdb);
        Titulo tituloM = new Titulo(meuTituloOmdb);
        System.out.println("TITULO JA CONVERTIDO");
        System.out.println(tituloM);

     //   Titulo meuTitulo = new Titulo(meuTituloOmdb.title())











































    }
}
