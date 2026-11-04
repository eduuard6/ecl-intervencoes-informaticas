package pt.ecl;

public class Html {
    // Mostra o texto escrito pelo utilizador sem o tratar como código HTML.
    public static String escapar(Object valor) {
        if (valor == null) return "";
        return valor.toString().replace("&", "&amp;")
            .replace("<", "&lt;").replace(">", "&gt;")
            .replace("\"", "&quot;").replace("'", "&#39;");
    }
}