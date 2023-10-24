import React from 'react'
import Link from 'next/link'
import Logo from '../components/Logo'

export default function page() {
  return (
    <div className='StartPage'>
      <Logo/>
      <div className = 'StartInfo'>
        <p className='StartTitle'>Have a nice time with your new friend</p>
               There may be restrictions on the use of abusive language.
               If you get a warning of more than five days, you will be expelled permanently.
      </div>
        <div className='ButtonBox'>
          <Link href = 'login'>
            <button className ='StartButton bg-blue-900  hover:bg-blue-500'>
              Start the chat
            </button>
          </Link>
          </div>
    </div>
  )
}
