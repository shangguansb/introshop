package com.example.IntroShop;


import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.IntroShop.customView.DividerItemDecoration;
import com.example.IntroShop.model._User;
import com.example.IntroShop.model.action;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by jamase on 2016-04-08.
 */
public class UserInfoFragement extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    String[] s = new String[]{"dwda", "dwhnudh", "dwhnudh",
            "dwhnudh", "dwhnudh", "dwhnudh", "dwhnudh", "dwhnudh", "dwhnudh"};
    SharedPreferences share;
    private SharedPreferences.Editor editor;
    _User user;
    public mUserInfoRecycleAdapter madapter;
    public String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.recycleadapterxml, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

//        share = getActivity().getSharedPreferences("_User", Context.MODE_PRIVATE);
//        editor = share.edit();
        if (getActivity().getIntent().getExtras() != null) {
            username = getActivity().getIntent().getExtras().get("username").toString();
//        BmobQuery<_User> queryuser = new BmobQuery<>();
//        queryuser.addWhereEqualTo("username", share.getString("username", null));
//        queryuser.findObjects(getActivity(), new FindListener<_User>() {
//            @Override
//            public void onSuccess(List<_User> object) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getActivity(), "查询用户对象成功！---", Toast.LENGTH_LONG).show();
//                for (_User user : object) {
//                    UserInfoFragement.this.user = user;
//                }
//
//            }
//
//            @Override
//            public void onError(int code, String msg) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getActivity(), "查询用户对象失败！---" + msg, Toast.LENGTH_LONG).show();
//            }
//        });
            BmobQuery<action> query = new BmobQuery<action>();
            query.addWhereEqualTo("username", username);
            query.order("updateAT");
            query.findObjects(getActivity(), new FindListener<action>() {

                @Override
                public void onSuccess(List<action> object) {
                    // TODO Auto-generated method stub
                    if (object.size() == 0) {
//                        RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.rel);
                        TextView text = (TextView) root.findViewById(R.id.text);
                        text.setVisibility(View.VISIBLE);
//                        relativeLayout.addView();
                    }
                    madapter = new mUserInfoRecycleAdapter(object);
                    mRecyclerView.setAdapter(madapter);
                    mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                    Toast.makeText(getActivity(), "查询用户动态成功！---" + object.size(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(int code, String msg) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getActivity(), "查询用户动态失败！---" + msg, Toast.LENGTH_LONG).show();
                }
            });
        }

        return root;
    }

    @Override
    public void onDestroy() {
        mRecyclerView.removeAllViews();
        mRecyclerView = null;
        super.onDestroy();
    }


//    private List<String> getRandomSublist(String[] array, int amount) {
//        ArrayList<String> list = new ArrayList<>(amount);
//        Random random = new Random();
//        while (list.size() < amount) {
//            list.add(array[random.nextInt(array.length)]);
//        }
//        return list;
//    }
//
//    public static class SimpleStringRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {
//
//        private final TypedValue mTypedValue = new TypedValue();
//        private int mBackground;
//        private List<String> mValues;
//
//        public static class ViewHolder extends RecyclerView.ViewHolder {
//            public String mBoundString;
//
//            public final View mView;
//
//            public final TextView mTextView;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//
//                mTextView = (TextView) view.findViewById(android.R.id.text1);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mTextView.getText();
//            }
//        }
//
//        public String getValueAt(int position) {
//            return mValues.get(position);
//        }
//
//        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
//            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
//            mBackground = mTypedValue.resourceId;
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.list_item, parent, false);
//            view.setBackgroundResource(mBackground);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mBoundString = mValues.get(position);
//            holder.mTextView.setText(mValues.get(position));
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Context context = v.getContext();
////                    Intent intent = new Intent(context, CheeseDetailActivity.class);
////                    intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mBoundString);
////
////                    context.startActivity(intent);
//                }
//            });
//
////            Glide.with(holder.mImageView.getContext())
////                    .load(Cheeses.getRandomCheeseDrawable())
////                    .fitCenter()
////                    .into(holder.mImageView);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//    }
//
//    public static final String[] sCheeseStrings = {
//            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
//            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
//            "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
//            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
//            "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
//            "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
//            "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
//            "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
//            "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
//            "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
//            "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
//            "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
//            "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)",
//            "Boeren Leidenkaas", "Bonchester", "Bosworth", "Bougon", "Boule Du Roves",
//            "Boulette d'Avesnes", "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
//            "Breakfast Cheese", "Brebis du Lavort", "Brebis du Lochois", "Brebis du Puyfaucon",
//            "Bresse Bleu", "Brick", "Brie", "Brie de Meaux", "Brie de Melun", "Brillat-Savarin",
//            "Brin", "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
//            "Briquette de Brebis", "Briquette du Forez", "Broccio", "Broccio Demi-Affine",
//            "Brousse du Rove", "Bruder Basil", "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
//            "Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase", "Button (Innes)",
//            "Buxton Blue", "Cabecou", "Caboc", "Cabrales", "Cachaille", "Caciocavallo", "Caciotta",
//            "Caerphilly", "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
//            "Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux", "Capricorn Goat",
//            "Capriole Banon", "Carre de l'Est", "Casciotta di Urbino", "Cashel Blue", "Castellano",
//            "Castelleno", "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
//            "Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou", "Chabichou du Poitou",
//            "Chabis de Gatine", "Chaource", "Charolais", "Chaumes", "Cheddar",
//            "Cheddar Clothbound", "Cheshire", "Chevres", "Chevrotin des Aravis", "Chontaleno",
//            "Civray", "Coeur de Camembert au Calvados", "Coeur de Chevre", "Colby", "Cold Pack",
//            "Comte", "Coolea", "Cooleney", "Coquetdale", "Corleggy", "Cornish Pepper",
//            "Cotherstone", "Cotija", "Cottage Cheese", "Cottage Cheese (Australian)",
//            "Cougar Gold", "Coulommiers", "Coverdale", "Crayeux de Roncq", "Cream Cheese",
//            "Cream Havarti", "Crema Agria", "Crema Mexicana", "Creme Fraiche", "Crescenza",
//            "Croghan", "Crottin de Chavignol", "Crottin du Chavignol", "Crowdie", "Crowley",
//            "Cuajada", "Curd", "Cure Nantais", "Curworthy", "Cwmtawe Pecorino",
//            "Cypress Grove Chevre", "Danablu (Danish Blue)", "Danbo", "Danish Fontina",
//            "Daralagjazsky", "Dauphin", "Delice des Fiouves", "Denhany Dorset Drum", "Derby",
//            "Dessertnyj Belyj", "Devon Blue", "Devon Garland", "Dolcelatte", "Doolin",
//            "Doppelrhamstufel", "Dorset Blue Vinney", "Double Gloucester", "Double Worcester",
//            "Dreux a la Feuille", "Dry Jack", "Duddleswell", "Dunbarra", "Dunlop", "Dunsyre Blue",
//            "Duroblando", "Durrus", "Dutch Mimolette (Commissiekaas)", "Edam", "Edelpilz",
//            "Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne", "Esbareich",
//            "Esrom", "Etorki", "Evansdale Farmhouse Brie", "Evora De L'Alentejo", "Exmoor Blue",
//            "Explorateur", "Feta", "Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
//            "Finlandia Swiss", "Finn", "Fiore Sardo", "Fleur du Maquis", "Flor de Guia",
//            "Flower Marie", "Folded", "Folded cheese with mint", "Fondant de Brebis",
//            "Fontainebleau", "Fontal", "Fontina Val d'Aosta", "Formaggio di capra", "Fougerus",
//            "Four Herb Gouda", "Fourme d' Ambert", "Fourme de Haute Loire", "Fourme de Montbrison",
//            "Fresh Jack", "Fresh Mozzarella", "Fresh Ricotta", "Fresh Truffles", "Fribourgeois",
//            "Friesekaas", "Friesian", "Friesla", "Frinault", "Fromage a Raclette", "Fromage Corse",
//            "Fromage de Montagne de Savoie", "Fromage Frais", "Fruit Cream Cheese",
//            "Frying Cheese", "Fynbo", "Gabriel", "Galette du Paludier", "Galette Lyonnaise",
//            "Galloway Goat's Milk Gems", "Gammelost", "Gaperon a l'Ail", "Garrotxa", "Gastanberra",
//            "Geitost", "Gippsland Blue", "Gjetost", "Gloucester", "Golden Cross", "Gorgonzola",
//            "Gornyaltajski", "Gospel Green", "Gouda", "Goutu", "Gowrie", "Grabetto", "Graddost",
//            "Grafton Village Cheddar", "Grana", "Grana Padano", "Grand Vatel",
//            "Grataron d' Areches", "Gratte-Paille", "Graviera", "Greuilh", "Greve",
//            "Gris de Lille", "Gruyere", "Gubbeen", "Guerbigny", "Halloumi",
//            "Halloumy (Australian)", "Haloumi-Style Cheese", "Harbourne Blue", "Havarti",
//            "Heidi Gruyere", "Hereford Hop", "Herrgardsost", "Herriot Farmhouse", "Herve",
//            "Hipi Iti", "Hubbardston Blue Cow", "Hushallsost", "Iberico", "Idaho Goatster",
//            "Idiazabal", "Il Boschetto al Tartufo", "Ile d'Yeu", "Isle of Mull", "Jarlsberg",
//            "Jermi Tortes", "Jibneh Arabieh", "Jindi Brie", "Jubilee Blue", "Juustoleipa",
//            "Kadchgall", "Kaseri", "Kashta", "Kefalotyri", "Kenafa", "Kernhem", "Kervella Affine",
//            "Kikorangi", "King Island Cape Wickham Brie", "King River Gold", "Klosterkaese",
//            "Knockalara", "Kugelkase", "L'Aveyronnais", "L'Ecir de l'Aubrac", "La Taupiniere",
//            "La Vache Qui Rit", "Laguiole", "Lairobell", "Lajta", "Lanark Blue", "Lancashire",
//            "Langres", "Lappi", "Laruns", "Lavistown", "Le Brin", "Le Fium Orbo", "Le Lacandou",
//            "Le Roule", "Leafield", "Lebbene", "Leerdammer", "Leicester", "Leyden", "Limburger",
//            "Lincolnshire Poacher", "Lingot Saint Bousquet d'Orb", "Liptauer", "Little Rydings",
//            "Livarot", "Llanboidy", "Llanglofan Farmhouse", "Loch Arthur Farmhouse",
//            "Loddiswell Avondale", "Longhorn", "Lou Palou", "Lou Pevre", "Lyonnais", "Maasdam",
//            "Macconais", "Mahoe Aged Gouda", "Mahon", "Malvern", "Mamirolle", "Manchego",
//            "Manouri", "Manur", "Marble Cheddar", "Marbled Cheeses", "Maredsous", "Margotin",
//            "Maribo", "Maroilles", "Mascares", "Mascarpone", "Mascarpone (Australian)",
//            "Mascarpone Torta", "Matocq", "Maytag Blue", "Meira", "Menallack Farmhouse",
//            "Menonita", "Meredith Blue", "Mesost", "Metton (Cancoillotte)", "Meyer Vintage Gouda",
//            "Mihalic Peynir", "Milleens", "Mimolette", "Mine-Gabhar", "Mini Baby Bells", "Mixte",
//            "Molbo", "Monastery Cheeses", "Mondseer", "Mont D'or Lyonnais", "Montasio",
//            "Monterey Jack", "Monterey Jack Dry", "Morbier", "Morbier Cru de Montagne",
//            "Mothais a la Feuille", "Mozzarella", "Mozzarella (Australian)",
//            "Mozzarella di Bufala", "Mozzarella Fresh, in water", "Mozzarella Rolls", "Munster",
//            "Murol", "Mycella", "Myzithra", "Naboulsi", "Nantais", "Neufchatel",
//            "Neufchatel (Australian)", "Niolo", "Nokkelost", "Northumberland", "Oaxaca",
//            "Olde York", "Olivet au Foin", "Olivet Bleu", "Olivet Cendre",
//            "Orkney Extra Mature Cheddar", "Orla", "Oschtjepka", "Ossau Fermier", "Ossau-Iraty",
//            "Oszczypek", "Oxford Blue", "P'tit Berrichon", "Palet de Babligny", "Paneer", "Panela",
//            "Pannerone", "Pant ys Gawn", "Parmesan (Parmigiano)", "Parmigiano Reggiano",
//            "Pas de l'Escalette", "Passendale", "Pasteurized Processed", "Pate de Fromage",
//            "Patefine Fort", "Pave d'Affinois", "Pave d'Auge", "Pave de Chirac", "Pave du Berry",
//            "Pecorino", "Pecorino in Walnut Leaves", "Pecorino Romano", "Peekskill Pyramid",
//            "Pelardon des Cevennes", "Pelardon des Corbieres", "Penamellera", "Penbryn",
//            "Pencarreg", "Perail de Brebis", "Petit Morin", "Petit Pardou", "Petit-Suisse",
//            "Picodon de Chevre", "Picos de Europa", "Piora", "Pithtviers au Foin",
//            "Plateau de Herve", "Plymouth Cheese", "Podhalanski", "Poivre d'Ane", "Polkolbin",
//            "Pont l'Eveque", "Port Nicholson", "Port-Salut", "Postel", "Pouligny-Saint-Pierre",
//            "Pourly", "Prastost", "Pressato", "Prince-Jean", "Processed Cheddar", "Provolone",
//            "Provolone (Australian)", "Pyengana Cheddar", "Pyramide", "Quark",
//            "Quark (Australian)", "Quartirolo Lombardo", "Quatre-Vents", "Quercy Petit",
//            "Queso Blanco", "Queso Blanco con Frutas --Pina y Mango", "Queso de Murcia",
//            "Queso del Montsec", "Queso del Tietar", "Queso Fresco", "Queso Fresco (Adobera)",
//            "Queso Iberico", "Queso Jalapeno", "Queso Majorero", "Queso Media Luna",
//            "Queso Para Frier", "Queso Quesadilla", "Rabacal", "Raclette", "Ragusano", "Raschera",
//            "Reblochon", "Red Leicester", "Regal de la Dombes", "Reggianito", "Remedou",
//            "Requeson", "Richelieu", "Ricotta", "Ricotta (Australian)", "Ricotta Salata", "Ridder",
//            "Rigotte", "Rocamadour", "Rollot", "Romano", "Romans Part Dieu", "Roncal", "Roquefort",
//            "Roule", "Rouleau De Beaulieu", "Royalp Tilsit", "Rubens", "Rustinu", "Saaland Pfarr",
//            "Saanenkaese", "Saga", "Sage Derby", "Sainte Maure", "Saint-Marcellin",
//            "Saint-Nectaire", "Saint-Paulin", "Salers", "Samso", "San Simon", "Sancerre",
//            "Sap Sago", "Sardo", "Sardo Egyptian", "Sbrinz", "Scamorza", "Schabzieger", "Schloss",
//            "Selles sur Cher", "Selva", "Serat", "Seriously Strong Cheddar", "Serra da Estrela",
//            "Sharpam", "Shelburne Cheddar", "Shropshire Blue", "Siraz", "Sirene", "Smoked Gouda",
//            "Somerset Brie", "Sonoma Jack", "Sottocenare al Tartufo", "Soumaintrain",
//            "Sourire Lozerien", "Spenwood", "Sraffordshire Organic", "St. Agur Blue Cheese",
//            "Stilton", "Stinking Bishop", "String", "Sussex Slipcote", "Sveciaost", "Swaledale",
//            "Sweet Style Swiss", "Swiss", "Syrian (Armenian String)", "Tala", "Taleggio", "Tamie",
//            "Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea", "Testouri",
//            "Tete de Moine", "Tetilla", "Texas Goat Cheese", "Tibet", "Tillamook Cheddar",
//            "Tilsit", "Timboon Brie", "Toma", "Tomme Brulee", "Tomme d'Abondance",
//            "Tomme de Chevre", "Tomme de Romans", "Tomme de Savoie", "Tomme des Chouans", "Tommes",
//            "Torta del Casar", "Toscanello", "Touree de L'Aubier", "Tourmalet",
//            "Trappe (Veritable)", "Trois Cornes De Vendee", "Tronchon", "Trou du Cru", "Truffe",
//            "Tupi", "Turunmaa", "Tymsboro", "Tyn Grug", "Tyning", "Ubriaco", "Ulloa",
//            "Vacherin-Fribourgeois", "Valencay", "Vasterbottenost", "Venaco", "Vendomois",
//            "Vieux Corse", "Vignotte", "Vulscombe", "Waimata Farmhouse Blue",
//            "Washed Rind Cheese (Australian)", "Waterloo", "Weichkaese", "Wellington",
//            "Wensleydale", "White Stilton", "Whitestone Farmhouse", "Wigmore", "Woodside Cabecou",
//            "Xanadu", "Xynotyro", "Yarg Cornish", "Yarra Valley Pyramid", "Yorkshire Blue",
//            "Zamorano", "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano"
//    };

}
