package com.github.lipenathan.teamcreator.services.persistence

/**
 * Interface que determina operações básicas de objeto de acesso à dados
 * @author fnanjos
 * @date 01/03/2023
 */
interface BasicDAO<T> {

    fun save(o: T)

    fun getById(id: Long): T?

    fun getAll(): List<T>
}