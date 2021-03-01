package in.notyouraveragedev.tensor_image_classification.Mantenedores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.notyouraveragedev.tensor_image_classification.ClasesSQLite.Conservacion;
import in.notyouraveragedev.tensor_image_classification.ClasesSQLite.Especie;
import in.notyouraveragedev.tensor_image_classification.ClasesSQLite.EstadoBiogeografico;

public class MantenedorEspecie extends SQLiteOpenHelper {
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "arbolesBBDD11.db";
    private static final String TABLA_ESTADOBIOGEOGRAFICO = "CREATE TABLE estbiogeografico (idEstadoBio TEXT PRIMARY KEY NOT NULL UNIQUE, desEstadoBio TEXT);";
    private static final String TABLA_CONSERVACION = "CREATE TABLE conservacion (idConservacion TEXT PRIMARY KEY NOT NULL UNIQUE, desConservacion TEXT);";
    private static final String TABLA_ESPECIE = "CREATE TABLE especie (idEspecie TEXT PRIMARY KEY NOT NULL UNIQUE, nomCientEspecie TEXT , nomComunEspecie TEXT, idEstadoBio TEXT, idConservacion TEXT, distribucionEspecie TEXT, descEspecie TEXT);";


    // Constructor
    public MantenedorEspecie(Context context){
        // Para crear base de datos
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);

    } // Fin Constructor MantendorProducto

    // Para crear la tabla. Solo la crea cuando no existe
    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(TABLA_CONSERVACION);
        db.execSQL("INSERT INTO conservacion VALUES ('LC','Preocupación menor')");
        db.execSQL("INSERT INTO conservacion VALUES ('NT','Casi amenazada')");
        db.execSQL("INSERT INTO conservacion VALUES ('VU','Vulnerable')");
        db.execSQL("INSERT INTO conservacion VALUES ('EN','En peligro')");
        db.execSQL("INSERT INTO conservacion VALUES ('CR','En peligro crítico')");
        db.execSQL("INSERT INTO conservacion VALUES ('EW','Extinta en estado silvestre')");
        db.execSQL("INSERT INTO conservacion VALUES ('EX','Extinta')");
        db.execSQL("INSERT INTO conservacion VALUES ('NF','Sin información')");

        db.execSQL(TABLA_ESTADOBIOGEOGRAFICO);
        db.execSQL("INSERT INTO estbiogeografico VALUES ('NAT','Nativa / Autóctona')");
        db.execSQL("INSERT INTO estbiogeografico VALUES ('END','Endémica')");
        db.execSQL("INSERT INTO estbiogeografico VALUES ('EXO','Exótica')");

        db.execSQL(TABLA_ESPECIE);

        db.execSQL("INSERT INTO especie VALUES ('0', 'Abies concolor', 'Abeto del Colorado', 'EXO', 'NF', 'Originaria de las montañas occidentales de Norteamérica, a altitudes de 900 a 3.400 msnm.','Es una conífera de medio a grande siempreverde que alcanza de 25 a 60 m de altura y con más de 2 m de diámetro de tronco. Las hojas son tipo aguja, chatas, de 2,5 a 6 cm de long. y 2 mm de ancho por 0,5 a 1 mm de espesor, verdes a azul verdoso glaucas arriba, y con dos bandas glaucas azul-blancas de estomas debajo, punta no aguzada. El arreglo foliar es en espiral en los ápices, pero cada hoja es variablemente retorcida en la base, pareciendo que hubiera dos o más líneas.')");
        db.execSQL("INSERT INTO especie VALUES ('1', 'Acer negundo L.', 'Arce, negundo, bordo, arce americano y/o arce de hojas de fresno', 'EXO', 'NF', 'Especie nativa de México hasta América del Norte, donde está presente de manera silvestre desde California a Florida, y hacia el norte, hasta Canadá (Hoffmann, 1998a; Chanes2006), ampliamente distribuida en todo el mundo. En Chile es una de las especies más cultivadas para arborización urbana a lo largo de todo el país','Árbol caducifolio, de forma esférica irregular que puede alcanzar hasta 20 m de altura. Fuste corto y generalmente con deformaciones. La corteza es lisa, de color marrón grisáceo, ligeramente hendida. Las ramas nuevas son de color verde recubiertas de un vello azulado.')");
        db.execSQL("INSERT INTO especie VALUES ('2', 'Acer pseudoplatanus L.', 'Arce, acer, falso plátano y/o arce sicomoro', 'EXO', 'NF', 'Especie originaria de Europa, donde se distribuye en el oeste, centro y sur del continente, llegando hacia el norte casi hasta el mar Báltico','Árbol caducifolio, de copa ovoidal, ramificada y expandida, que puede alcanzar hasta 30 m de altura y entre 6 a 8 m de diámetros de copas. Fuste recto, con una corteza lisa, de color gris café, que se torna agrietada y escamosa a mayor desarrollo, estas se desprende en pequeñas placas aplanadas. Hojas opuestas, grandes y palmeadas')");
        db.execSQL("INSERT INTO especie VALUES ('3', 'Baccharis linearis', 'Romerillo', 'NAT', 'NF', 'Habita en terrenos áridos y degradados, tanto en la Cordillera de la Costa como en los Andes, entre las regiones de Atacama y de la Araucanía.En Argentina es propia de los montes serranos en las Provincias de Córdoba y San Luis','Es una especie de hábito de crecimiento sub-arbustivo (1,5 a 2,0 m de altura), de sexualidad diclino-dioica con una dispersión de sus semillas de tipo anemócora. Esta especie conforma la sucesión temprana en suelos degradados, es resistente a sequía y a baja disponibilidad de nutrientes en suelos')");
        db.execSQL("INSERT INTO especie VALUES ('4', 'Bauhinia candicans Benth', 'Bauhinia, pata de vaca, pata de chivo, pezuña de vaca, pata de buey, árbol de las orquídeas, falsa caoba y/o caoba del país', 'EXO', 'NF', 'Especie sudamericana más específicamente de países como Perú, Bolivia, Brasil, Paraguay, Uruguay y Argentina. Es ampliamente usado como árbol urbano en América del Sur, sobre todo en zonas de abundante sol y pocas heladas.','Árbol caducifolio o semicaducifolio, de copa redondeada, que puede alcanzar hasta 15 m de altura. Fuste recto, de corteza delgada y persistente, de color pardo claro, las ramas algo pubescentes, a veces con aguijones estipulares curvos en los nudos. Hojas bilobuladas, alternas, pecioladas, de color verde claro, de 8 a 12 cm de longitud, de margen entero, glabras en el haz, glabras o tomentosas en el envés, con una nervadura muy marcada de un color verde más claro, ovadas, de base truncada o acorazonado, con lóbulos agudos o subagudos y se asemeja a la huella de una pezuña, de allí el nombre de “pata de vaca”, posee aguijones curvos pareados en las axilas de las hojas')");
        db.execSQL("INSERT INTO especie VALUES ('5', 'Peumus boldus Molina', 'Boldo, foldu, boldu o folo', 'END', 'LC', 'Se distribuye entre las provincias del Elqui y Osorno, desde el nivel del mar hasta los 1.000 metros de altitud','Arbol siempreverde, de copa amplia. Mide hasta 20 metros de altura y el tronco alcanza cerca de 1 metro de diámetro. La corteza es gris-parda, ligeramente rugosa y agrietada en los árboles más viejos. Sus hojas son duras, aromáticas y muy ásperas al tacto')");
        db.execSQL("INSERT INTO especie VALUES ('6', 'Brachychiton populneus', 'Peral del Japón, brachichito, braquiquito, braquiquito blanco, árbol botella, bracho y/o kurrajong (Australia)', 'EXO', 'NF', 'Especie originaria de Australia (Nueva Gales del Sur, noroeste de Victoria y Queensland), muy utilizado en las zonas de clima templado','Árbol siempreverde, a veces semicaducifolio, de copa densa y piramidal, que puede alcanzar hasta 20 m de altura y 4 a 8 m de diámetro de copa. Fuste recto, corteza de color cenicienta a verde oscura y relativamente lisa. Ramas exteriores colgantes (López y Sánchez, 2001). Hojas alternas, de 5 a 8 cm de longitud, de forma variable, por lo general ovaladas y enteras; también es posible encontrar algunas profundamente lobuladas, largamente pecioladas, glabras y brillantes, con el envés verde claro')");
        db.execSQL("INSERT INTO especie VALUES ('7', 'Caesalpinia spinosa (Mol.) Kuntze', 'Tara, taro, tara espinosa, taya, algarroba tanino, goma del Perú, goma tara, acacia amarilla y/o huarango', 'NAT', 'NF', 'Especie nativa de Sudamérica (Argentina, Bolivia, Chile, Colombia, Ecuador, Perú y Venezuela), aunque generalmente se señala como árbol nativo del Perú desde donde se ha distribuido a toda América Latina. Se utiliza en países como Marruecos, India y China. En Chile se puede tratar como una especie introducida en la época de los Incas o anterior. Se encuentra principalmente desde la Región de Arica y Parinacota hasta la Región de Coquimbo','Árbol o arbusto siempreverde, de copa globosa no muy densa, que puede alcanzar hasta 12 m de altura. Fuste tortuoso, espinoso con varios ejes y que puede llegar a tener 30 cm de diámetro, de corteza rugosa, color marrón claro o gris ceniza. Las ramas son cortas retorcidas, resistentes, grises y estriadas, las cuales poseen espinas cónicas, cortas y fuertes. Hojas compuestas, alternas, bipinnaticompuestas')");
        db.execSQL("INSERT INTO especie VALUES ('8', 'Colliguaja odorifera', 'Colliguay, Coliguay, Lechón', 'END', 'LC', 'Se puede encontrar desde Antofagasta hasta Linares, desde el litoral hasta 2000 m.s.n.m.','Arbusto siempreverde de hasta 2 m de alto, de corteza café-pardo y que se desprende con facilidad., liberando látex (sustancia gomosa) de color blanco si se le tala. Las hojas son de de color verde claro, elípticas, opuestas, de ápice redondeado o agudo. Los bordes son aserrados')");
        db.execSQL("INSERT INTO especie VALUES ('9', 'Cupressus ssp', 'Ciprés', 'EXO', 'FC', ' Se han naturalizado en todas las regiones templadas del Hemisferio Norte, con las temperaturas y suelos adecuados y se pueden encontrar creciendo espontáneamente y de forma dispersa. Se cultivan comercialmente en África oriental, Sudáfrica y Nueva Zelanda.','Como la gran mayoría de las coníferas, son de hoja perenne, pueden alcanzar los 20 m de altura con un diámetro aproximado de unos 60 cm. Su porte es piramidal, de crecimiento rápido en los primeros años de vida, ralentizándose después y pudiendo alcanzar los 300 años de vida. Poseen un tronco recto y de corteza delgada en la que se forman fisuras longitudinales. Las hojas son muy pequeñas (2-6 mm de longitud) con forma de escama, alineadas en parejas opuestas y decusadas.')");
        db.execSQL("INSERT INTO especie VALUES ('10', 'Cytisus scoparius', ' Retama negra o escoba rubia', 'EXO', 'LC', 'Generalizada en casi toda la península ibérica, así como en el resto de la Europa atlántica.','Es una planta arbustiva de 1 a 2 m de altura, con ramas delgadas, estriadas, de color verde y con pocas hojas. Flor amarilla y papilionada. El fruto es una legumbre negra pilosa. La floración es de abril a julio. Tiene uso medicinal por sus propiedades cardiotónicas y estimulantes. Las propiedades cardiotónicas son controvertidas y su gran uso es como diurético y antiedematoso.')");
        db.execSQL("INSERT INTO especie VALUES ('11', 'Erythrina umbrosa Kunth', 'Bucare, ceibo, búcare, búcaro, cachimbo, cañaro, palo prieto, peronía y/o seibo', 'EXO', 'NF', 'Especie de origen sudamericano de la zona subtropical, de países como Argentina, Brasil, Paraguay y Uruguay','Árbol parcialmente caducifolio, dependiendo en gran\n" +
                "medida de la intensidad del frío durante el invierno, de\n" +
                "gran tamaño, con ramificaciones gruesas que generan una\n" +
                "copa globosa amplísima y que se desgancha fácilmente,\n" +
                "por ser su madera muy quebradiza puede alcanzar hasta\n" +
                "25 m de altura. Fuste recto y corto, de corteza de color\n" +
                "café-grisácea, con fisuras verticales (Hoffmann, 1998a).\n" +
                "Hojas alternas, compuestas trifoliadas y armadas de espinas\n" +
                "blandas sobre el nervio medio, con pequeñas estipulas\n" +
                "en la base, de borde entero, ligeramente ondulado, de\n" +
                "nervadura marcadísima')");
        db.execSQL("INSERT INTO especie VALUES ('12', 'Escallonia pulverulenta', 'Corontillo, colantillo, corantillo, madroño o siete camisas', 'END', 'LC', 'Se encuentra entre las provincias de Limarí y Cautín. Crece en laderas asoleadas de ambas cordilleras y en bordes de quebradas, hasta los 1.100 metros de altitud.','Arbol siempreverde, peludo o resinoso, de hasta 12 metros de altura. El tronco alcanza un diámetro de unos 40 cm. La corteza es gris oscura y se desprende en angostas fajas longitudinales. Tiene una bella inflorescencia cilíndrica, compuesta por flores blancas y aromáticas, cuya forma se asemeja a la coronta del choclo')");
        db.execSQL("INSERT INTO especie VALUES ('13', 'Acacia caven (Molina) Molina', 'Espino, espino maulino, churque, churco, espinillo, caven, espinillo o kawen', 'NAT', 'LC', 'En Chile se encuentra entre las provincias de Copiapó y del Biobío. Habita desde la costa hasta la precordillera, por lo general, en climas secos y terrenos pobres. Es una especie difundida en zonas áridas y semiáridas de América del Sur','Arbol espinoso, bota las hojas en invierno o verano, o las conserva por hasta dos años dependiendo de la latitud. Puede llegar a medir cerca de 10 metros de altura. El tronco es tortuoso, de unos 50 cm de diámetro. La corteza es gruesa, agrietada longitudinalmente, opaca y negruzca. El fruto es una legumbre negra indehiscente, que se conoce como quiringa o quirincho.')");
        db.execSQL("INSERT INTO especie VALUES ('14', 'Eucalyptus spp', 'Eucaliptos o eucaliptas', 'EXO', 'NF', 'Existen alrededor de setecientas especies, la mayoría oriundas de Australia y Nueva Guinea. En la actualidad, se encuentran distribuidos por gran parte del mundo y, debido a su rápido crecimiento, frecuentemente se emplean en plantaciones forestales.','Los eucaliptos son árboles y plantas medicinales perennes pirófitas, de porte recto. Pueden llegar a medir más de 60 m de altura, si bien se habla de ejemplares ya desaparecidos que han alcanzado los 150 m. En algunos ejemplares la corteza exterior (ritidoma) es marrón clara con aspecto de piel y se desprende a tiras dejando manchas grises o parduscas sobre la corteza interior, más lisa. Las hojas jóvenes de los eucaliptos son sésiles, ovaladas, grisáceas y de forma falciforme. Estas se alargan y se tornan de un color verde azulado brillante de adultas; contienen un aceite esencial, de característico olor balsámico, que es un poderoso desinfectante natural. ')");
        db.execSQL("INSERT INTO especie VALUES ('15', 'Ficus carica', 'Higuera', 'EXO', 'NF', 'Originario de Asia sudoccidental, crece ahora espontáneamente en torno al Mediterráneo y en otras regiones del mundo.','Su fruto es el higo. Árbol o arbusto caducifolio de porte bajo, su altura máxima es de 7-8 m. De copa muy abierta debido a su profusa ramificación, que a menudo surge casi a ras del suelo.\n" +
                "La corteza es lisa y de color grisáceo. Las hojas, de 12 a 25 cm de largo y 10 a 18 cm de ancho, son profundamente lobuladas, formadas por 3 o 7 folíolos, de color verde brillante y textura áspera.')");
        db.execSQL("INSERT INTO especie VALUES ('16', 'Fraxinus excelsior L.', 'Fresno, fresno europeo, fresno norteño y/o fresno común', 'EXO', 'NF', 'Especie originaria de Europa y Asia Menor, en el hemisferio\n" +
                "norte crece de manera natural en muchos lugares.\n" +
                "En Chile esta especie es ampliamente utilizada para\n" +
                "arbolado urbano, teniendo una buena representatividad.\n" +
                "Es común encontrarla entre la Región de Coquimbo\n" +
                "hasta la del Biobío','Árbol caducifolio, de copa extendida, densa e irregular, que\n" +
                "puede alcanzar hasta 45 m de altura y 8 m de diámetro\n" +
                "de copa (Hoffmann, 1998a). Fuste recto, grueso y corteza\n" +
                "café oscura algo rojiza que se agrieta al envejecer.\n" +
                "Hojas opuestas, de 20 a 30 cm de longitud, compuestas\n" +
                "imparipinadas, de 9 a 13 folíolos opuestos con excepción\n" +
                "del terminal, peciolados, sésiles, de borde aserrado, folíolos\n" +
                "lanceolados a oval-oblongos y agudos en el ápice,')");
        db.execSQL("INSERT INTO especie VALUES ('17', 'Gleditsia triacanthos L.', 'Árbol de las tres espinas, acacia de tres espinas, acacia negra y/o acacia de tres púas', 'EXO', 'NF', 'Especie originaria de Norteamérica, más específicamente\n" +
                "en Estados Unidos en las zonas este, central y sur (Hoffmann,\n" +
                "1998a; FIA-INDAP-INFOR, 2000a; Rodríguez, et al.,\n" +
                "2005; Chanes, 2006; Gutiérrez, 2006). Habita también el\n" +
                "norte de México, y en Sudamérica se la puede encontrar\n" +
                "en Argentina, Uruguay y Chile','Árbol caducifolio, de copa redondeada, que puede alcanzar\n" +
                "hasta 40 m de altura. Fuste recto, de corteza de color oscuro,\n" +
                "algo rugosa y provista de grupo de espinas ramificadas.\n" +
                "Hoja compuesta, pinnada o bipinnada, alternas, de 14 a 30\n" +
                "cm de longitud, compuesta por 20 a 30 folíolos pequeños,\n" +
                "con 4 a 9 pares de pinnas; los folíolos de 1 a 4 cm de longitud,\n" +
                "oblongo-lanceolados, sésiles, de borde ligeramente\n" +
                "aserrado, algo peludos en el dorso, de color verde claro y\n" +
                "brillante, el que se torna amarillo al comenzar el otoño.')");
        db.execSQL("INSERT INTO especie VALUES ('18', 'Porlieria chilensis', 'Guayacán, huayacán o palo santo', 'END', 'VU', 'Se desarrolla en forma natural entre Limarí y Colchagua (IV a VI región), hasta los 1.300 msnm, frecuentemente en laderas y lugares rocosos. Es un componente común en el bosque esclerófilo, del que forma parte. ','Arbusto  o  árbol  pequeño, de hasta 4m de alto, con ramas gruesas y tortuosas, con hojas perennes, compuestas. Estipulas espinescentes. Flores solitarias, hermafroditas, pequeñas y violáceas. Fruto una cápsula morada dehiscente, de 4 a 5 lóbulos muy profundos. Semillas numerosas.')");
        db.execSQL("INSERT INTO especie VALUES ('19', 'Schinus polygamus', 'Huingán, Borocoi o Boroco', 'END', 'LC', 'Este arbusto es frecuente en el matorral mediterráneo chileno, tanto en la costa como en el interior, entre Atacama y Valdivia. Se desarrollan en suelos pobres y áridos, asoleados tanto en las partes altas como bajas de la cordillera. Prefiere los suelos pobres de exposición norte y se desarrolla tanto en planicies como en laderas. Hacia su dispersión norte se describe la variedad parviflorus, de hojas más anchas, más arbustivo y más florífero.','Arbolito o arbusto familia Anacardiácea y siempre verde, glabro, de 1-2,5 m de altura, con ramificación desde la base y terminando éstas en espinas. Tronco de 15-30 cm de diámetro. Hojas alternas, pecioladas, de 1,5-2,5 cm de largo, con forma variable, cercana a oblonga. Sobre las ramas se generan agallas, como resultado de una infección provocada por insectos. Las flores son polígamas y dioicas, de 4-5 mm de diámetro, dispuestas en racimos densos axilares. Cáliz de 4-5 divisiones, 4-5 pétalos, flores masculinas con 10 estambres. La floración es durante septiembre y diciembre. El fruto es una drupa esférica, de 5 mm de diámetro, color violeta oscuro. ')");
        db.execSQL("INSERT INTO especie VALUES ('20', 'Jacaranda mimosifolia David Don', 'Jacarandá, tarco, falso palisandro\n" +
                "y/o simplemente palisandro', 'EXO', 'NF', 'Especie de origen subtropical o tropical nativo de Sudamérica\n" +
                "(Argentina, Bolivia, Brasil, Paraguay y Uruguay). Como\n" +
                "árbol ornamental es utilizado en numerosas partes del\n" +
                "mundo (Australia, España, Estados Unidos, México, Portugal,\n" +
                "Sudáfrica, etc. En Chile, se cultiva como árbol urbano en\n" +
                "el norte y centro del país.','Árbol siempreverde o semicaducifolio, de copa esférica e\n" +
                "irregular, que puede alcanzar hasta 15 m de altura y un\n" +
                "diámetro de copa de 6 a 8 m. Fuste recto, aunque a veces\n" +
                "es algo torcido, con corteza arrugada de color café oscuro\n" +
                "cuando envejece y de corteza lisa de color marrón grisácea\n" +
                "en etapa juvenil.\n" +
                "Hojas grandes, compuestas bipinnadas y opuestas.')");
        db.execSQL("INSERT INTO especie VALUES ('21', 'Kageneckia oblonga', 'Bollén, guayo, huayú o huayo colorado', 'END', 'LC', 'Crece entre las provincias del Elqui y del Biobío.\n" +
                "Habita en ambas cordilleras y el valle central. Se adapta fácilmente\n" +
                "a terrenos secos o semihúmedos, alcanzando hasta los 1.800 metros\n" +
                "de altitud en el caso de la Cordillera de los Andes.','Arbol siempreverde, puede llegar a medir hasta 15 metros de altura,\n" +
                "aunque lo más común es encontrar ejemplares de unos 4 metros.\n" +
                "El tronco alcanza unos 40 cm de diámetro. La corteza es cenicienta\n" +
                "pardusca y con fisuras longitudinales en los ejemplares añosos. Las\n" +
                "hojas son duras y aserradas en el margen.')");
        db.execSQL("INSERT INTO especie VALUES ('22', 'Ligustrum lucidum', 'Ligustro, alheña, aligustre, ligustrina, aligustre del Japón, ligustro chino y/o ligustro ceroso.', 'EXO', 'NF', 'Especie de origen asiático, más específicamente de China,\n" +
                "Corea y Japón.','Árbol o arbusto siempreverde, de copa redondeada y\n" +
                "densa, que puede alcanzar hasta los 15 m de altura.\n" +
                "Fuste recto, cilíndrico con ramificaciones densas, de\n" +
                "corteza lisa, de color gris verdoso, que se fisura longitudinalmente\n" +
                "con la edad. Puede tener un solo eje\n" +
                "fustal o múltiples ejes dependiendo del manejo que se\n" +
                "le aplique. Es la especie más grande del género, cualidad\n" +
                "que la destaca sobre el resto.\n" +
                "Hojas simples, opuestas, coriáceas, de pecíolo corto, ovaladas\n" +
                "a oblongas de color verde oscuro brillante en el haz y verde\n" +
                "pálido en el envés. Los bordes de las hojas y la nervadura\n" +
                "mediana son de tonos rojizos')");
        db.execSQL("INSERT INTO especie VALUES ('23', 'Liquidambar styraciflua L.', 'Liquidámbar, liquidámbar americano,\n" +
                "gomero dulce, árbol del estoraque y/o nogal satinado', 'EXO', 'NF', 'Especie originaria de áreas templadas del este de Norteamérica\n" +
                "y el norte de Centroamérica (Estados Unidos,\n" +
                "México y Guatemala). En Chile y parte de Sudamérica se\n" +
                "ha introducido de manera exitosa.','Árbol caducifolio, de copa piramidal y simétrica, que puede\n" +
                "alcanza hasta 40 m de altura, pero en ambientes urbanos\n" +
                "no sobrepasa los 20 m y 4 a 8 m de diámetro de copa.\n" +
                "Fuste recto, de corteza gris claro, profundamente hendida\n" +
                "y suberosa, incluso en las ramas jóvenes.')");
        db.execSQL("INSERT INTO especie VALUES ('24', 'Liriodendron tulipifera L.', 'Tulipero, árbol de los tulipanes\n" +
                "y/o tulipanero', 'EXO', 'NF', 'Especie originaria del este de Norteamérica, particularmente\n" +
                "frecuente al sur de los montes Apalaches,\n" +
                "en Carolina del Norte y en el Estado de Tennessee.','Árbol caducifolio, de copa redonda o piramidal y simétrica\n" +
                "que puede alcanzar hasta 50 m de altura y 6 a 8 m de\n" +
                "diámetro de copa. Fuste recto y ramificación que comienza\n" +
                "bastante abajo. La corteza muy fisurada cuando madura,\n" +
                "de color café anaranjado, algo oscura.')");
        db.execSQL("INSERT INTO especie VALUES ('25', 'Lithrea caustica', 'Litre o litri', 'END', 'LC', 'Se encuentra entre las provincias de\n" +
                "Coquimbo y del Biobío, desde el nivel del mar hasta los 1.500\n" +
                "metros de altitud. Es uno de los árboles más frecuentes de los\n" +
                "bosques esclerofilos.','Arbol siempreverde, de copa globosa. Puede llegar a medir\n" +
                "hasta 15 metros de altura, aunque en la actualidad lo más común\n" +
                "es encontrarlo como arbusto. El tronco alcanza unos 50 cm de\n" +
                "diámetro. Es muy característico de esta especie el contraste entre\n" +
                "la nervadura amarilla y la lámina verde de sus hojas duras.')");
        db.execSQL("INSERT INTO especie VALUES ('26', 'Magnolia grandiflora L.', 'Magnolia y/o magnolio', 'EXO', 'NF', 'Especie originaria del sureste de los Estados Unidos\n" +
                "desde Carolina del Norte hasta Texas y Florida, es muy\n" +
                "frecuente verla cultivada como planta ornamental en\n" +
                "Chile y el resto del mundo.','Árbol siempreverde, de copa piramidal o redondeada y\n" +
                "follaje denso, que puede alcanzar hasta 30 m de altura, de\n" +
                "ramas recogidas e irregulares. Fuste recto que se bifurca\n" +
                "a cierta altura, corteza gruesa, de color gris-castaña con\n" +
                "hendiduras poco profundas y escamas delgadas')");
        db.execSQL("INSERT INTO especie VALUES ('27', 'Maytenus boaria', 'Maitén', 'NAT', 'LC', 'En Chile se encuentra entre las provincias de Huasco y Última\n" +
                "Esperanza, desde el nivel del mar hasta los 1.800 metros de altitud.\n" +
                "Es muy común en tierras bajas, tanto en laderas como en planos bien\n" +
                "drenados. También crece de manera natural en Argentina, Perú y\n" +
                "Brasil.','Arbol siempreverde, de copa redondeada, frondosa y ramas colgantes.\n" +
                "Alcanza una altura de 15 metros y su tronco mide hasta 80 cm de\n" +
                "diámetro. La corteza es grisácea y agrietada, desprendiéndose en\n" +
                "los ejemplares adultos.')");
        db.execSQL("INSERT INTO especie VALUES ('28', 'Sophora macrocarpa', 'Mayú, mayu o mayo', 'END', 'LC', 'Crece entre la Región de Valparaíso y la de la Araucanía, en sectores abiertos o como parte del sotobosque.','Es un arbusto de unos 3 metros de alto, multicaulinar, posee hojas perenne, compuestas e imparipinadas de 6 a 15 cm, con 12 a 25 folíolos, normalmente elípticos u ovalados de 15 a 30 mm de largo, con tomento blanquecino por el envés, presenta estipulas. Flores zigomorfas, de color amarillo, con estandarte más largo que quilla y alas, de pedúnculos largos, sobre racimos cortos (de hasta 10 cm). Su fruto es una legumbre aterciopelada, fuertemente estrangulada entre cada septo, la cual contiene de 1 a 6 semillas, de color café o amarillo oscuro, de forma esférica y superficie irregular de 8 a 12 mm de diámetro. Sus hojas, flores y frutos son muy semejantes a aquellos del pelú (Sophora cassioides), pero de mayor tamaño. Se considera que entre ambas especies puede producirse hibridación. ')");
        db.execSQL("INSERT INTO especie VALUES ('29', 'Melia azedarach L.', 'Paraíso, árbol del paraíso,\n" +
                "melia, lila de la India, revienta caballos,\n" +
                "jabonero de las Antillas, cinamomo\n" +
                "y/o árbol de los rosarios', 'EXO', 'NF', 'Especie originaria de los montes Himalaya, y actualmente\n" +
                "se encuentra naturalizada en todas las regiones tropicales\n" +
                "y subtropicales del mundo. Se difundió a mediados del\n" +
                "siglo XIX como ornamental en Sudáfrica y América, donde\n" +
                "se naturalizó con rapidez, convirtiéndose en una especie\n" +
                "invasora que desplazó otras autóctonas.','Árbol caducifolio o semicaducifolio en algunos casos, de copa\n" +
                "globosa y extendida, que puede alcanzar hasta 15 m de altura\n" +
                "y 6 a 8 m de diámetro de copa. Fuste recto y ramas frágiles,\n" +
                "de corteza oscura, fisurada y rugosa.')");
        db.execSQL("INSERT INTO especie VALUES ('30', 'Morus alba', 'Morera', 'EXO', 'NF', 'Son árboles oriundos de las zonas templadas de Asia central y del Este (China, Manchuria y Corea) y muy cultivado en Asia, Europa y América. ','Árboles de hasta 15 m de altura, con ramas jóvenes grisáceas. Hojas con pecíolo de 1,5-2 cm y limbo de 4-6 por 4-5 cm, más o menos ovado, subagudo, irregularmente dentado o lobado, oblicuamente cordado, delgado, glabro excepto a lo largo de la nerviación, verde claro. Infrutescencias (sorosis) de la longitud de sus pedúnculos (2,5 por 1 cm), blancas o blanco-rosadas, que son las moras.')");
        db.execSQL("INSERT INTO especie VALUES ('31', 'Myoporum laetum G. Forst', 'Transparente, brillante, mioporo y/o siempreverde', 'EXO', 'NF', 'Especie originaria de Nueva Zelanda. Se cultiva con frecuencia en España, Portugal y Australia entre otros países. En general, en zonas de clima cálido o suave. En Chile es utilizado en la zona norte y centro del país','Árbol pequeño o arbusto siempreverde, de copa globosa y densa, que puede alcanzar hasta 12 m de altura, aunque normalmente es de 4 a 6 m. Fuste con uno o varios ejes más o menos torcido, corteza de color pardo grisácea, algo rugosa y agrietada')");
        db.execSQL("INSERT INTO especie VALUES ('32', 'Parkinsonia aculeata', 'Cina-cina , Parquinsonia , Espina de Jerusalem , Palo verde', 'EXO', 'NF', 'Es nativo del sudoeste de Estados Unidos (oeste de Tejas y sur de Arizona), México y Sudamérica (Argentina, Uruguay, Paraguay, Bolivia, Ecuador, Perú, islas Galápagos). Es una especie también ampliamente cultivada como ornamental en zonas templadas y subtropicales a pesar de tener un elevado potential invasor.','Árbol de la subfamilia Caesalpinioideae, dentro de la familia de las leguminosas. Es un pequeño árbol espinoso que alcanza hasta los 10 m de altura con tronco en principio verde y luego agrietado y con las ramas nuevas y ramillas -zigzagueantes con el ángulo al nivel de las inserciones foliares- que se quedan verdes hasta volverse adultas.')");
        db.execSQL("INSERT INTO especie VALUES ('33', 'Crinodendron patagua', 'Patagua y/o patahua', 'END', 'LC', 'Se distribuye entre las provincias de Quillota y\n" +
                "Arauco. Habita en sitios húmedos, a la orilla de esteros o ríos, hasta\n" +
                "los 1.200 metros de altitud.','Arbol siempreverde, ramoso y de copa redondeada. Alcanza a\n" +
                "crecer hasta 15 metros de altura y su tronco mide cerca de 1 metro\n" +
                "de diámetro. La corteza es gris e irregularmente agrietada.')");
        db.execSQL("INSERT INTO especie VALUES ('34', 'Paulownia tomentosa', 'Paulonia, árbol dedalero\n" +
                "y/o árbol de la emperatriz', 'EXO', 'NF', 'Especie originaria de China central y Japón. Por su belleza\n" +
                "esta especie esta muy extendida ornamentalmente en los\n" +
                "diferentes lugares del mundo, y en especial en los países\n" +
                "de clima templado.','Árbol caducifolio, de copa globosa, ramas gruesas y extendidas,\n" +
                "muy parecido a la Catalpa bignonioides, puede\n" +
                "alcanzar hasta 25 m de altura y 8 a 14 m de diámetro de\n" +
                "copa. Fuste recto, corteza lisa y de color gris en su etapa\n" +
                "juvenil, y en su madurez de color gris oscuro con una\n" +
                "superficie acanalada')");
        db.execSQL("INSERT INTO especie VALUES ('35', 'Cryptocarya alba', 'Peumo o pengu', 'END', 'LC', 'Se encuentra entre las provincias de\n" +
                "Coquimbo y Valdivia, siendo abundante entre Petorca y Curicó.\n" +
                "Habita en ambas cordilleras y el valle central, preferentemente\n" +
                "en quebradas y laderas sombrías, desde el nivel del mar hasta\n" +
                "los 1.500 metros de altitud.','Arbol siempreverde, de follaje denso y verde oscuro. Llega a\n" +
                "medir hasta 20 metros de altura y el tronco, recto o ligeramente\n" +
                "tortuoso, cerca de 1 metro de diámetro. La corteza es delgada,\n" +
                "de color café claro y agrietada. Las hojas son duras, con un\n" +
                "aroma intenso y característico, y una notoria diferencia de\n" +
                "coloración entre ambos lados, siendo el superior de un verde\n" +
                "brillante y el inferior de un verde pálido.')");
        db.execSQL("INSERT INTO especie VALUES ('36', 'Schinus molle L.', 'Pimiento, pimiento boliviano, pimentero, falsa pimienta, molle,\n" +
                "molli, aguaribay, huaribay, cuyash, kullakz, anacahuita o pirul', 'NAT', 'NF', 'Se encuentra de manera relativamente natural entre las provincias\n" +
                "de Arica y Santiago, aunque se ha extendido más al sur mediante\n" +
                "el cultivo como árbol ornamental. Crece en una amplia variedad\n" +
                "de ambientes, siendo muy resistente a la sequía y a los suelos\n" +
                "salinos, desde el nivel del mar hasta los 3.500 metros de altitud.','Arbol siempreverde, de copa densa y muy ramificado en la parte\n" +
                "superior. Alcanza una altura de hasta 25 metros y su tronco llega\n" +
                "a medir 1,5 metros de diámetro. La corteza va desde un color café\n" +
                "claro a ligeramente grisáceo, es áspera y agrietada, y se desprende\n" +
                "en los ejemplares más viejos.')");
        db.execSQL("INSERT INTO especie VALUES ('37', 'Platanus orientalis L.', 'Plátano oriental', 'EXO', 'NF', 'Especie originaria del sureste de Europa y de Asia Occidental.\n" +
                "A la fecha es una especie cosmopolita muy usada\n" +
                "como ornamental.','Árbol caducifolio, de copa ancha y redondeada, de gran\n" +
                "altura, que alcanza fácilmente los 30 m en ciudad.\n" +
                "Fuste recto y grueso, con una corteza de color amarillo\n" +
                "verdoso que se desprende en placas anchas y delgadas,\n" +
                "produciendo una textura moteada muy atractiva')");
        db.execSQL("INSERT INTO especie VALUES ('38', 'Prunus cerasifera Ehrh.', 'Pruno, ciruelo de flor, ciruelo mirobolano,\n" +
                "mirobálano, ciruelo-cerezo y/o ciruelo de jardín', 'EXO', 'NF', 'Especie originaria de Europa central y este, también de Asia.','Árbol caducifolio, de copia amplia y esférica que puede alcanzar\n" +
                "hasta 15 m de altura y 4 m de diámetro de copa. Fuste recto,\n" +
                "aunque algunas veces algo torcido, de corteza lisa y oscura.\n" +
                "Hojas simples, alternas, enteras, elíptica, de 4 a 6 cm de\n" +
                "longitud, glabras, salvo el nervio central en el envés, con\n" +
                "borde aserrado, nervadura marcada y ápice agudo. El follaje\n" +
                "original de la especie es de color verde claro, pero la\n" +
                "variedad pissardii lo tiene rojo purpúreo.')");
        db.execSQL("INSERT INTO especie VALUES ('39', 'Prunus persica', 'Melocotonero, durazno o duraznero', 'EXO', 'NF', 'El melocotonero es un frutal que extiende su presencia por la Europa Meridional (España, Italia, Francia, Grecia, Turquía, Israel), China, América del Norte y del Sur y por África del Norte (Marruecos principalmente).','Árbol de hasta 6-8 m de altura, caducifolio e inerme. Las hojas son oblongas-lanceoladas o elípticas, acuminadas, cuneadas en la base, aserradas con dientes glandulíferos, glabrescentes, con estípulas caducas denticuladas. Su fruto, el melocotón o durazno, contiene una única semilla encerrada en una cáscara dura, el «hueso». Esta fruta, normalmente de piel aterciopelada, posee una carne amarilla o blanquecina de sabor dulce y aroma delicado. A la variedad que no tiene la piel aterciopelada se la llama nectarina, pelón o pavía.')");
        db.execSQL("INSERT INTO especie VALUES ('40', 'Senna candolleana', 'Alcaparra o quebracho', 'END', 'NF', 'Se distribuye desde la Región de Coquimbo hasta la Región del Libertador General Bernardo OHiggins. Crece en valles interiores desde altitudes que van hasta los 700 msnm.','Es un arbusto que, naturalmente alcanza una altura de 1 - 5 metros. De copa redondeada y follaje denso, con forma globosa y hojas gruesas, semicoriáceas compuestas de 6 o 7 pares de folíolos y de 10 cm de largo. Las flores son llamativas, de un color amarillo, reunidas en inflorescencias (Corimbo), con floración desde el invierno hasta comienzos del verano. El fruto es una legumbre angosta, larga e indehiscente, con las semillas separadas por tabiques. Su maduración se produce en otoño. ')");
        db.execSQL("INSERT INTO especie VALUES ('41', 'Quercus robur L.', 'Encina, roble (en España), encina\n" +
                "inglesa, roble europeo, roble de Eslavonia y/o carvallo.', 'EXO', 'NF', 'Especie originaria de la mayor parte de Europa, este de Asia\n" +
                "y norte de África. En Chile es utilizada en la zona centro y sur.','Árbol caducifolio, de copa amplia, aovada, redondeada\n" +
                "o irregular, que puede alcanzar hasta 50 m de altura. En\n" +
                "Chile es común entre los 25 a 30 m. Fuste recto, corto y\n" +
                "muy grueso en los ejemplares aislados, las ramificaciones\n" +
                "salen desde baja altura, de corteza de color gris oscuro muy\n" +
                "resquebrajada y tonalidad pardusca en los ejemplares viejos.')");
        db.execSQL("INSERT INTO especie VALUES ('42', 'Quillaja saponaria', 'Quillay o küllai', 'END', 'LC', 'Se distribuye entre las provincias del Elqui\n" +
                "y Arauco. Crece en ambas cordilleras y en el valle central,\n" +
                "desde el nivel del mar hasta los 1.600 metros de altitud.','Arbol siempreverde, de copa ancha. Llega a medir hasta 15\n" +
                "metros de altura y su tronco cerca de 1 metro de diámetro. La\n" +
                "corteza es cenicienta y rasgada longitudinalmente. Sus hojas\n" +
                "son duras y con dientes en su borde. Es muy característico el\n" +
                "contraste entre sus hojas verde claras y su tronco oscuro.')");
        db.execSQL("INSERT INTO especie VALUES ('43', 'Ricinus communis', 'Ricino, castor, tártago, higuereta, higuerilla, higuera infernal o mosquitera', 'EXO', 'NF', 'El ricino es originario de África tropical, alrededor de Etiopía, pero se ha naturalizado en áreas tropicales y subtropicales de todo el mundo convirtiéndose en una maleza invasora.','El ricino o higuerilla es un arbusto de tallo grande y leñoso, hueco que, al igual que los peciolos, nervios e incluso las propias hojas en algunas variedades, puede tomar un color púrpura oscuro y suele estar cubierto de un polvillo blanco, semejante a la cera. Las hojas son muy grandes, de nervación palmeada y hendidas de 5 a 9 lóbulos, de bordes irregularmente dentados; las hojas son alternas, con peciolo muy largo, unido por su parte inferior. Produce gran cantidad de semillas altamente viables y sumamente venenosas. Se calcula que tan solo cuatro semillas pueden matar a un adulto de tamaño medio. Sin embargo, de esta planta se pueden extraer compuestos anticancerígenos y el aceite de ricino, que es comestible. Esto mediante un proceso de separación del aceite. También es una especie ornamental preferida en jardines.')");
        db.execSQL("INSERT INTO especie VALUES ('44', 'Schinus latifolia', 'Molle o lilén', 'END', 'NF', 'Habita principalmente en la zona litoral y\n" +
                "en la Cordillera de la Costa, entre las provincias de Limarí y\n" +
                "Talca.','Arbol siempreverde, alcanza hasta 10 metros de altura. El tronco\n" +
                "es tortuoso y llega a medir unos 50 cm de diámetro. La corteza,\n" +
                "de color café, se descascara con la edad. Las hojas tienen un\n" +
                "agradable aroma cítrico al ser molidas.')");
        db.execSQL("INSERT INTO especie VALUES ('45', 'Ulmus americana L.', 'Olmo, olmo americano\n" +
                "y/o olmo blanco', 'EXO', 'NF', 'Especie originaria del este y centro de Estados Unidos.\n" +
                " El género agrupa a unas 20 especies\n" +
                "de hoja caduca, todas provenientes de las regiones\n" +
                "templadas del hemisferio norte, encontrándose tanto\n" +
                "en Europa como en Asia y África.','gran número de ramificaciones, que pueden alcanzar\n" +
                "hasta 40 m de altura. Fuste recto y con la corteza grisácea\n" +
                "y fisurada. Hojas simples, alternas, aovado-oblongas a\n" +
                "elípticas, asimétricas en la base, de 5 a 15 cm de longitud,\n" +
                "más anchas hacia la mitad, y de ancho de 2 a 8 cm,\n" +
                "con el ápice agudo, de borde groseramente aserrado,\n" +
                "pecioladas y tienen la cara superior glabra y áspera al\n" +
                "tacto, y la inferior, pubescente, con la nervadura muy\n" +
                "marcada.')");


    } // Fin onCreate

    // Cambia la version de la tabla cuando se actualiza
    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version){
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLA_ESTADOBIOGEOGRAFICO + "';");
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLA_CONSERVACION + "';");
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLA_ESPECIE + "';");
    } // Fin onUpgrade


    // Aqui vienen los metodos a utilizar en la programacion

    public void insertarConservacion(Conservacion conservacion){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            db.execSQL("INSERT INTO conservacion (idConservacion, desConservacion) VALUES ('" + conservacion.getIdConservacion() + "','" +
                    conservacion.getDesConservacion() + "');");
        }
        db.close();
    } // Fin insertarConservacion

    public void insertarEstadoBiogeografico(EstadoBiogeografico estBioGeo){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            db.execSQL("INSERT INTO estbiogeografico (idEstadoBio, desEstadoBio) VALUES ('" + estBioGeo.getIdEstadoBio() + "','" +
                    estBioGeo.getDesEstadoBio() + "');");
        }
        db.close();
    } // Fin insertarEstadoBiogeografico

    public List<String> consultaConservacion() {
        SQLiteDatabase db = getReadableDatabase();
        List<String> auxListaConservacion = new ArrayList<>();
        // agregar fecha
        Cursor auxCursor = db.rawQuery("SELECT desConservacion FROM conservacion", null);
        auxCursor.moveToFirst();

        do {
            String auxDesCon = auxCursor.getString(0);
            auxListaConservacion.add(auxDesCon);

        } while (auxCursor.moveToNext());
        db.close();
        auxCursor.close();
        return auxListaConservacion;
    }

    public Especie buscaEspecie(String idEspecie){

        SQLiteDatabase db = getReadableDatabase();

        Especie auxEspecie = new Especie();

        Cursor auxCursor = db.rawQuery("SELECT idEspecie, nomCientEspecie, nomComunEspecie, estbiogeografico.desEstadoBio, conservacion.desConservacion, distribucionEspecie, descEspecie FROM especie INNER JOIN conservacion ON especie.idConservacion = conservacion.idConservacion INNER JOIN estbiogeografico ON especie.idEstadoBio = estbiogeografico.idEstadoBio"
                + " WHERE idEspecie = '" + idEspecie + "';", null);

        auxCursor.moveToFirst();

        if(auxCursor != null){
            auxEspecie.setIdEspecie(auxCursor.getString(0));
            auxEspecie.setNomCientEspecie(auxCursor.getString(1));
            auxEspecie.setNomComunEspecie(auxCursor.getString(2));
            auxEspecie.setIdEstadoBio(auxCursor.getString(3));
            auxEspecie.setIdConservacion(auxCursor.getString(4));
            auxEspecie.setDistribucionEspecie(auxCursor.getString(5));
            auxEspecie.setDescEspecie(auxCursor.getString(6));
        }
        else{
            auxEspecie.setIdEspecie("");
            auxEspecie.setNomCientEspecie("");
            auxEspecie.setNomComunEspecie("");
            auxEspecie.setIdEstadoBio("");
            auxEspecie.setIdConservacion("");
            auxEspecie.setDistribucionEspecie("");
            auxEspecie.setDescEspecie("");
        }
        db.close();
        auxCursor.close();
        return auxEspecie;

    } // Fin buscaEspecie

    public List<String> consultaEspecies(){

        SQLiteDatabase db = getReadableDatabase();

        List<String> auxListaEspecie = new ArrayList<>();
        // agregar fecha
        Cursor auxCursor = db.rawQuery("SELECT nomCientEspecie FROM especie", null);

        auxCursor.moveToFirst();

        do{
            String auxNombCientEspecie = auxCursor.getString(0);
            auxListaEspecie.add(auxNombCientEspecie);

        } while(auxCursor.moveToNext());
        db.close();
        auxCursor.close();
        return auxListaEspecie;

    } // Fin consultaCLiente

} // FIn

