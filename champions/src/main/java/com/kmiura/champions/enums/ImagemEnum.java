package com.kmiura.champions.enums;

public enum ImagemEnum {
    SPLASH, MINIATURA, LOADING;

    @Override
	public String toString() {
		switch (this) {
		case SPLASH:
			return "Splash";
		case MINIATURA:
			return "Miniatura";
		case LOADING:
			return "Loading";
		default:
			return null;
		}
	}

}
