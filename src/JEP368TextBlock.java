import javax.script.ScriptException;

/**
 * <a href="https://openjdk.org/jeps/378">Text Blocks</a>
 */
public class JEP368TextBlock {
    public static void main(String[] args) throws ScriptException {
        var html = "<html>\n" +
            "    <body>\n" +
            "        <p>Hello, world</p>\n" +
            "    </body>\n" +
            "</html>\n";

        var html1 = """
            <html>
                <body>
                    <p>Hello, world</p>
                </body>
            </html>
            """;
        System.out.println(html);
        System.out.println(html1);
        System.out.println(html1.equals(html));
        System.out.println(html1 == html);

        String query = "SELECT \"EMP_ID\", \"LAST_NAME\" FROM \"EMPLOYEE_TB\"\n" +
            "WHERE \"CITY\" = 'INDIANAPOLIS'\n" +
            "ORDER BY \"EMP_ID\", \"LAST_NAME\";\n";

        String query1 = """
            SELECT "EMP_ID", "LAST_NAME" FROM "EMPLOYEE_TB"
            WHERE "CITY" = 'INDIANAPOLIS'
            ORDER BY "EMP_ID", "LAST_NAME";
            """;

        System.out.println(query1);

        // === error ===
        // String a = """""";
        // String b = """ """;
        // String c = """
        //             ";
        // String d = """
        //    abc \ def
        //    """;
        // === error ===
    }
}
