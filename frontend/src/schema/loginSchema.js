import * as Yup from 'yup';

export const loginSchema = Yup.object().shape({
    username:Yup.string().required("username require!"),
    password:Yup.string().required("password require!")
})