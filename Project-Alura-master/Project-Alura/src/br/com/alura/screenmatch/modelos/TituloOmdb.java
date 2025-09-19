package br.com.alura.screenmatch.modelos;

//declaramos os dados imutaveis para convertemos
public record TituloOmdb(String title, String year, String runtime) {

}








// o que este Record esta fazendo ele esta pegando os valores que estão como titulo e ano
//isso pq o json estar retornando com outro nome, fazendo isso pegamos os valores que estaão com
//esse nome e preenchemos