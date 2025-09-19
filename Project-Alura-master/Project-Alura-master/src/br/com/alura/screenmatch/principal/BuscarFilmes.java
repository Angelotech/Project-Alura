package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class BuscarFilmes {
    public static void main(String[] args) throws IOException, InterruptedException {
        String buscar = "";
        Scanner entrada =  new Scanner(System.in);
        System.out.print("digite o nome do filme: ");

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create(); // isso faz com as requição dos campos title entre outras fiquem maiusculas

        while (!buscar.equalsIgnoreCase("sair")) {
            buscar  = entrada.nextLine();

            ArrayList<Titulo> lista_filmes = new ArrayList<>();

            if(buscar.equalsIgnoreCase("sair")){
                break;
            }

            try {
                String endereco = ("https://www.omdbapi.com/?t=" + buscar + "&apikey=bbc3db2");

                // 1. Criando o cliente HTTP
                // Esse é o "objeto" que vai enviar a requisição e receber a resposta
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco)) /* -> aqui fica a url da Api  */
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String RespostaDoCorpo = response.body();

                TituloOmdb meuTituloOmdb = gson.fromJson(RespostaDoCorpo, TituloOmdb.class); // resposta do corpo + classe que qeuremos tranforma o mesmo
                System.out.println(meuTituloOmdb);

                Titulo tituloM = new Titulo(meuTituloOmdb);
                lista_filmes.add(tituloM);

                FileWriter escrita = new FileWriter("filmes.txt");
                escrita.write(lista_filmes.toString());
                escrita.close();


                System.out.println("");
                System.out.println(tituloM);
                System.out.println("TITULO JA CONVERTIDO");

            } catch (NumberFormatException e) {
                System.out.println("este é o erro");
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("este foi o erro apresentado " + e.getMessage());

            } catch (ControleDeException e) {
                System.out.println("o erro de exceção foi esse " + e.getMessage());
            }

            FileWriter escrita = new FileWriter("filmes.json");
            escrita.write(gson.toJson(lista_filmes));
            escrita.write(lista_filmes.toString());
            escrita.close();


        }


        System.out.println("programa finalizado");

        //   Titulo meuTitulo = new Titulo(meuTituloOmdb.title())











































    }
}
