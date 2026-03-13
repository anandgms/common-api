package edu.anand.web.request;

public record ApiPageableRequest<T>(int page, int rows, T criteria) {}
