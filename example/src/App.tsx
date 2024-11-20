import { useState } from 'react';
import {
  Dimensions,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';
import { MapComposeView } from 'react-native-map-compose';

const { width } = Dimensions.get('window');
const style = { width, height: width };
export default function App() {
  const [show, hide] = useState(true);
  return (
    <View style={styles.container}>
      {show ? <MapComposeView style={style} /> : null}
      <TouchableOpacity
        onPress={() => {
          hide(!show);
        }}
      >
        <Text style={{ padding: 20, backgroundColor: 'black' }}>
          {show ? 'HIDE' : 'SHOW'}
        </Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
