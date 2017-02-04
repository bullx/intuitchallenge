import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class match {

	static HashMap<String, Float> hm;
	static HashMap<String, Float> categories;

	public match() {

	}

	public static void main(String[] args) {
		String location = "output.csv";
		FileWriter writer = null;
		try {
			writer = new FileWriter(location);
			writer.append("Expenses");
			writer.append(',');
			writer.append("Amount Spent");
			writer.append('\n');
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		hm = new HashMap<String, Float>();
		for (int i = 0; i < 5; i++) {
			String csvFile = "user-" + i + ".csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			int c = 0;
			String userId = "";
			try {

				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {

					if (c != 0) {

						// use comma as separator
						String[] data_user = line.split(cvsSplitBy);

						userId = data_user[0];
						if (!hm.containsKey(data_user[2])) {

							hm.put(data_user[2], Float.valueOf(data_user[3].substring(1, data_user[3].length())));
						} else {

							float temp = hm.get(data_user[2]);
							temp += Float.valueOf(data_user[3].substring(0, data_user[3].length()));
							hm.put(data_user[2], temp);
						}
					}
					c = 1;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				writer.append("User Id : " + userId.toString());
				writer.append(',');
				writer.append('\n');
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			for (String k : hm.keySet()) {
				try {

					writer.append(k);
					writer.append(',');
					writer.append(hm.get(k).toString());
					writer.append('\n');

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				writer.append('\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
			hm.clear();
			System.out.println("");
			System.out.println("");
		}

		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
