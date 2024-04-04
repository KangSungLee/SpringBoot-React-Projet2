import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';


function App() {
  const [data, setData] = useState('');
  const [user, setUser] = useState({});
  const [list, setList] = useState([]);
  useEffect(() => {
    axios.get('/rp/react/data')
      .then(res => {
        console.log(res);
        setData(res.data);
      })
      .catch(err => {console.log(err);});
  }, []);
  useEffect(() => {
    axios.get('/rp/react/json')
      .then(res => {setUser(res.data)})
      .catch(err => console.log(err))
  }, []);
  useEffect(() => {
    axios.get('/rp/react/list')
      .then(res => {
        setList(res.data)
        console.log(res + '아무값도 없나');
      })
      .catch(err => console.log(err))
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h3>
          받아온 값: {data ? data : '받아오지 못했어요'}
        </h3>
        <h3>
          사용자 정보: uid={user.uid}, uname={user.uname}
        </h3>
        <table border={1} style={{width: 500}}>
          <tr>
            <th>ID</th><th>이름</th><th>이메일</th>
          </tr>
          {
            list.map((x) => (
              <tr>
                <td>{x.uid}</td><td>{x.uname}</td><td>{x.email}</td>
              </tr>  
            )
          )}
        </table>
      </header>
    </div>
  );
}

export default App;