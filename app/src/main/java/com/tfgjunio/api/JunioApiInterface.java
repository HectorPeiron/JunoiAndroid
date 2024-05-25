package com.tfgjunio.api;


import com.tfgjunio.domain.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JunioApiInterface {

    //ANIMALES
    @GET("/animales")
    Call<List<Animal>> getAnimales();

    @POST("/tipoAnimales/{tipoAnimalId}/crianzas/{crianzaId}/animales")
    Call<Animal> addAnimal(@Body Animal animal);

    @DELETE("/animales/{id}")
    Call<Void> deleteAnimales(@Path("id") long id);

    @PUT("/animales/{id}/{tipoAnimalId}/{crianzaId}")
    Call<Animal> modifyAnimal(@Path("id") long id, @Body Animal animal);


    //COMPRA
    @GET("/compras")
    Call<List<Compra>> getCompras();

    @POST("/recursos/{recursoId}/crianzas/{crianzaId}/compras")
    Call<Compra> addCompra(@Body Compra compra);

    @DELETE("/compras/{id}")
    Call<Void> deleteCompras(@Path("id") long id);

    @PUT("/compras/{id}/{recursoId}/{crianzaId}")
    Call<Compra> modifyCompra(@Path("id") long id, @Body Compra compra);


    //CRIANZA
    @GET("/crianzas")
    Call<List<Crianza>> getCrianzas();

    @POST("/animales/{animalId}/compras/{compraId}/veterinarios/{veterinarioId}/crianzas")
    Call<Crianza> addCrianza(@Body Crianza crianza);

    @DELETE("/crianzas/{id}")
    Call<Void> deleteCrianza(@Path("id") long id);

    @PUT("/crianzas/{id}/{animalId}/{compraId}/{veterinarioId}")
    Call<Crianza> modifyCrianza(@Path("id") long id, @Body Crianza crianza);



    //RECURSO
    @GET("/recursos")
    Call<List<Recurso>> getRecursos();

    @POST("/tipoRecursos/{tipoRecursoId}/tipoAnimales/{tipoAnimalId}/unidades/{unidadId}/recursos")
    Call<Recurso> addRecurso(@Body Recurso recurso);

    @DELETE("/recursos/{id}")
    Call<Void> deleteRecurso(@Path("id") long id);

    @PUT("/recursos/{id}/{tipoRecursoId}/{tipoAnimalId}/{unidadId}")
    Call<Recurso> modifyRecurso(@Path("id") long id, @Body Recurso recurso);


    //VETERINARIO
    @GET("/veterinarios")
    Call<List<Veterinario>> getVeterinarios();

    @POST("/crianzas/{crianzaId}/veterinarios")
    Call<Veterinario> addVeterinario(@Body Veterinario veterinario);

    @DELETE("/veterinarios/{id}")
    Call<Void> deleteVeterinario(@Path("id") long id);

    @PUT("/veterinarios/{id}/{crianzaId}")
    Call<Veterinario> modifyVeterinario(@Path("id") long id, @Body Veterinario veterinario);

    //TIPO ANIMAL
    @GET("/tipoAnimales/{tipoAnimalesId}")
    Call<List<TipoAnimal>> getTipoAnimales();

    @POST("/tipoAnimales")
    Call<TipoAnimal> addTipoAnimal(@Body TipoAnimal tipoAnimal);

    //TIPO RECURSO
    @GET("/tipRecursos/{tipRecursosId}")
    Call<List<TipoRecurso>> getTipoRecursos();

    @POST("/tipRecursos")
    Call<TipoRecurso> addTipoRecurso(@Body TipoRecurso tipoRecurso);

    //UNIDAD
    @GET("/unidades/{unidadesId}")
    Call<List<Unidad>> getUnidades();

    @POST("/unidades")
    Call<Unidad> addUnidad(@Body Unidad unidad);

}
