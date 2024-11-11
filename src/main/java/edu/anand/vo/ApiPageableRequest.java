package edu.anand.vo;

public record ApiPageableRequest<T>(int page, int rows, T criteria) {}
