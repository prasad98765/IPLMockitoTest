import java.util.*;
import java.util.stream.Collectors;

public class IPLAnalyser {

    Map<String,CricketDataDAO> playerList = null;
    LoadDataFactory loadDataFactory ;
    public Player player;

    public IPLAnalyser(LoadDataFactory loadDataFactory, Player player) {
        this.loadDataFactory = loadDataFactory;
        this.player = player;
    }

    public enum Player {
        Batting, Bowling;
    }

    public IPLAnalyser(Player player) {
        this.player = player;
    }

    public int lodeIPLCricketData(String... csvFilePath) throws IPLAnalyserException {
        playerList = loadDataFactory.loadCricketData(player, csvFilePath);
        return playerList.size();
    }

    public ArrayList getSortedDatafieldsWise(FieldWiseSorting.fields parameter) {
        Comparator<CricketDataDAO> batsmanComparator = null;
            batsmanComparator = new FieldWiseSorting().getParameterFields(parameter);
        ArrayList batsmanList = playerList.values().stream()
                .sorted(batsmanComparator)
                .map(batsmanDAO -> batsmanDAO.getIPLDTO(player))
                .collect(Collectors.toCollection(ArrayList::new));
        return batsmanList;
    }
}
