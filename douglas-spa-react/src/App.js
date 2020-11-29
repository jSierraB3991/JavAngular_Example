import SeriesApp from "./Components/App/SeriesApp";
import { MovieDetail } from './Components/MovieDetail/MovieDetail'
import { BrowserRouter as Router, Route} from 'react-router-dom'

function App() {
  return (
    <Router>
      <Route path="/" exact component={SeriesApp} />
      <Route path="/:id" exact component={MovieDetail} />
    </Router>
  );
}

export default App;
