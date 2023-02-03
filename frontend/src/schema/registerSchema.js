import * as Yup from 'yup';

export const registerSchema = Yup.object().shape({
    username:Yup.string().required(),
    firstname:Yup.string().required(),
    lastname:Yup.string().required(),
    password:Yup.string().required(),
    confirmPassword:Yup.string().oneOf([Yup.ref('password')], 'Passwords must match').required(),
    town:Yup.string().required(),
    isSaver:Yup.boolean().required(),
    isSender:Yup.boolean().required()
})