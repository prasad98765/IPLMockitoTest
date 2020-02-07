import java.util.Map;

public class LoadDataFactory {
    public Map<String, CricketDataDAO> loadCricketData(IPLAnalyser.Player player, String... csvFilePath) throws IPLAnalyserException {
        if (player.equals(IPLAnalyser.Player.Batting)) {
            return new BattingAdapter().loadIPLRunsPlayerData(csvFilePath);
        }
        if (player.equals(IPLAnalyser.Player.Bowling)) {
            return new BowlingAdapter().loadIPLRunsPlayerData(csvFilePath);
        }
        else {
            throw new IPLAnalyserException("INCORRECT_COUNTRY", IPLAnalyserException.ExceptionType.INCORRECT_TYPE);
        }
    }
}
