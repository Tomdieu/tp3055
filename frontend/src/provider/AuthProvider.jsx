import React, { useEffect, useState } from "react";

import { AuthContext } from "../context/AuthContext";
import ApiService from "../utils/ApiService";

const AuthProvider = ({ children }) => {
  const [userId, setUserId] = useState(null);
  const [userInfo, setUserInfo] = useState(null);
  const login = async (data) => {
    return await ApiService.login(data);
  };

  useEffect(() => {
    const token = localStorage.getItem("userId");
    if (token) {
      setUserId(token);
    } else {
      setUserId(null);
    }
    const user = localStorage.getItem("userInfo");
    if (user) {
      setUserInfo(JSON.parse(user));
    } else {
      setUserInfo(null);
    }
  }, []);

  useEffect(() => {
    if (userId) {
      localStorage.setItem("userId", userId);
    }
    if (userInfo) {
      localStorage.setItem("userInfo", JSON.stringify(userInfo));
    }
  }, [userId, userInfo]);

  const logout = () => setUserId(null);

  return (
    <AuthContext.Provider
      value={{ userId, login, setUserId, userInfo, setUserInfo,logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
